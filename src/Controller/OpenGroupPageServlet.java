package Controller;
import Schedule.BSUIRTeacher;
import Schedule.JSONRequester;
import Schedule.JSONTeachersParser;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MainPageServlet", value = "/BSUIRSchedule")
public class OpenGroupPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONRequester teachersRequester = new JSONRequester();
        JSONArray array = new JSONArray(teachersRequester.getTeachers());
        JSONTeachersParser teachersParser = new JSONTeachersParser(array);
        ArrayList<BSUIRTeacher> arr = teachersParser.parseToList();
        System.out.println(arr);
        String path = "/Pages/groupScheduleStart.html";
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
