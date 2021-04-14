import Schedule.BSUIRSchedule;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ScheduleServlet", value = "/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String grNumber =  request.getParameter("groupNumber");
        PrintWriter writer = response.getWriter();
        try {
            BSUIRSchedule schedule = new BSUIRSchedule(grNumber);
            writer.println(schedule.getForCurrentWeekHTML());
        } catch (JSONException e) {
            writer.println("Error of schedule reading");
        }
    }
}
