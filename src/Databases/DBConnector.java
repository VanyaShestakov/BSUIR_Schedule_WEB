package Databases;

import JSON.JSONRequester;
import JSON.JSONTeachersParser;
import Schedule.BSUIRTeacher;
import com.mysql.cj.jdbc.Driver;
import org.json.JSONArray;

import java.sql.*;
import java.util.ArrayList;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/teachers_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private Statement statement;

    public DBConnector() {
        try {
            java.sql.Driver driver = new Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeachersInfoTable() {
        ArrayList<BSUIRTeacher> teachers = getTeachersArr();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.addBatch("TRUNCATE TABLE teachers_info");
            for (BSUIRTeacher teacher: teachers) {
                String data  = "(" + teacher.getId() + ", '" + teacher.getFirstName() + "', '" + teacher.getLastName() + "', '" +
                        teacher.getMiddleName() + "', '" + teacher.getRank() +"', '" + teacher.getPhotoLink() + "', '" + teacher.getFio() + "')";
                statement.addBatch("INSERT INTO teachers_info (id, first_name, last_name, middle_name, `rank`, photo_link, fio) " +
                        "VALUES " + data);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BSUIRTeacher> getTeachersFromDB() {
        ArrayList<BSUIRTeacher> teachers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    private ArrayList<BSUIRTeacher> getTeachersArr() {
        JSONRequester teachersRequester = new JSONRequester();
        JSONArray array = new JSONArray(teachersRequester.getTeachers());
        JSONTeachersParser teachersParser = new JSONTeachersParser(array);
        return teachersParser.parseToList();
    }
}