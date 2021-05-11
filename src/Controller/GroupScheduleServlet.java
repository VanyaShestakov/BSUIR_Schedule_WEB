package Controller;
import Databases.DBConnector;
import HTML.HTMLWriter;
import Schedule.BSUIRSchedule;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "ScheduleServlet", value = "/BSUIRSchedule")
public class GroupScheduleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isEmptyCookies = request.getCookies() == null || Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("groupNumber"))
                .count() != 1;
        if (!isEmptyCookies) {
            forwardGroupSchedulePage(request, response, getGroupNumberFromCookies(request));
        } else {
            String path = "/Pages/groupSchedule.jsp";
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addCookie(new Cookie("groupNumber", request.getParameter("groupNumber")));
        forwardGroupSchedulePage(request, response, request.getParameter("groupNumber"));
    }

    private void forwardGroupSchedulePage(HttpServletRequest request, HttpServletResponse response, String groupNumber) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String path = "/Pages/groupSchedule.jsp";
        String schedule = "";
        try {
            HTMLWriter writer = new HTMLWriter(new BSUIRSchedule(groupNumber));
            request.setAttribute("chosenGroup", groupNumber);
            schedule = writer.getScheduleInfo() + writer.getHTMLSchedule();
        } catch (JSONException e) {
            schedule = "JSON Error";
        }
        request.setAttribute("schedule", schedule);
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

}
