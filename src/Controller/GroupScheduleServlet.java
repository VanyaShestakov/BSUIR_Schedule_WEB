package Controller;
import HTML.HTMLWriter;
import Schedule.BSUIRSchedule;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ScheduleServlet", value = "/BSUIRSchedule")
public class GroupScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/Pages/groupSchedule.jsp";
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String path = "/Pages/groupSchedule.jsp";
        String schedule = "";
        try {
            HTMLWriter writer = new HTMLWriter(new BSUIRSchedule(request.getParameter("groupNumber")));
            request.setAttribute("chosenGroup", request.getParameter("groupNumber"));
            schedule = writer.getScheduleInfo() + writer.getHTMLSchedule();
        } catch (JSONException e) {
            schedule = "JSON Error";
        }
        request.setAttribute("schedule", schedule);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}
