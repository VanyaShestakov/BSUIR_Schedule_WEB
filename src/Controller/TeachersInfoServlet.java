package Controller;

import Databases.DBConnector;
import HTML.HTMLWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.StringJoiner;

@WebServlet(name = "TeachersInfoServlet", value = "/teachers")
public class TeachersInfoServlet extends HttpServlet {
    private final static String JSP_PATH = "/Pages/teachersInfo.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(JSP_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teachersInfo = "";
        if (req.getParameter("SEARCH") != null) {
            teachersInfo = getTeachers(req.getParameter("teacherName"));
        } else if (req.getParameter("SEARCH_ALL") != null) {
            teachersInfo = getAllTeachers();
        }
        req.setAttribute("teachersInfo", teachersInfo);
        getServletContext().getRequestDispatcher(JSP_PATH).forward(req, resp);
    }

    private String getTeachers(String teacherName) {
        HTMLWriter writer = new HTMLWriter();
        DBConnector connector = new DBConnector();
        return writer.getTeachersInfo(connector.getTeachersWithFIO(teacherName));
    }

    private String getAllTeachers() {
        HTMLWriter writer = new HTMLWriter();
        DBConnector connector = new DBConnector();
        return writer.getTeachersInfo(connector.getTeachersFromDB());
    }
}
