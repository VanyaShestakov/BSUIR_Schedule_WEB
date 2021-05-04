package Controller;

import Databases.DBConnector;
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
        DBConnector connector = new DBConnector();
        connector.getTeachersFromDB();
        String path = "/Pages/teachersInfo.jsp";
        getServletContext().getRequestDispatcher(path).forward(req, resp);
    }
}
