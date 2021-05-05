package Controller;

import Databases.DBConnector;
import HTML.HTMLWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "TeachersInfoServlet", value = "/teachers")
public class TeachersInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/Pages/teachersInfo.jsp";
        getServletContext().getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/Pages/teachersInfo.jsp";
        String teacherName = req.getParameter("teacherName");
        HTMLWriter writer = new HTMLWriter();
        DBConnector connector = new DBConnector();
        String teachersInfo = writer.getTeachersInfo(connector.getTeachersWithFIO(teacherName));
        req.setAttribute("teachersInfo", teachersInfo);
        getServletContext().getRequestDispatcher(path).forward(req, resp);
    }
}
