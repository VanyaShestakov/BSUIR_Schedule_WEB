package Controller;
import Databases.DBConnector;
import HTML.HTMLWriter;
import Schedule.BSUIRSchedule;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Set;

@WebServlet(name = "ScheduleServlet", value = "/BSUIRSchedule")
public class GroupScheduleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isEmptyCookies = request.getCookies() == null || Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("groupNumber"))
                .count() != 1;
        if (!isEmptyCookies) {
            setScheduleAttr(request, getGroupNumberFromCookies(request));
            forwardGroupSchedulePage(request, response);
        } else {
            String path = "/Pages/groupSchedule.jsp";
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupNumber = request.getParameter("groupNumber");
        DBConnector groupsTableConnector = new DBConnector();
        if (/*isCorrectGroupNumber(groupNumber) &&*/ groupsTableConnector.containsGroup(groupNumber.trim())) {
            response.addCookie(new Cookie("groupNumber", request.getParameter("groupNumber")));
            setScheduleAttr(request, groupNumber);
            forwardGroupSchedulePage(request, response);
        } else {
            request.setAttribute("alert","" +
                    "<script language=\"JavaScript\"> "+
                    "alert(\"Ошибка. Запрашиваемая Вами группа не найдена!\"); " +
                    "</script>");
            forwardGroupSchedulePage(request, response);
        }
    }

    private void forwardGroupSchedulePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String path = "/Pages/groupSchedule.jsp";
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private String getGroupNumberFromCookies(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("groupNumber"))
                .findFirst()
                .get()
                .getValue();
    }

    private void setScheduleAttr(HttpServletRequest request, String groupNumber) {
        String schedule = "";
        try {
            HTMLWriter writer = new HTMLWriter(new BSUIRSchedule(groupNumber));
            request.setAttribute("chosenGroup", groupNumber);
            schedule = writer.getScheduleInfo() + writer.getHTMLSchedule();
        } catch (JSONException e) {
            schedule = "JSON Error";
        }
        request.setAttribute("schedule", schedule);
    }

    private boolean isCorrectGroupNumber (String groupNumber) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<BSUIRSchedule>> errors = validator.validateValue(BSUIRSchedule.class, "groupNumber", groupNumber);
        validatorFactory.close();
        return errors.size() == 0;
    }


}
