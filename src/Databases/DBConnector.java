package Databases;

import JSON.Parsers.GroupsParser;
import JSON.Parsers.JSONParser;
import JSON.JSONRequester;
import JSON.Parsers.TeachersParser;
import Schedule.BSUIRGroup;
import Schedule.BSUIRTeacher;
import com.mysql.cj.jdbc.Driver;
import org.json.JSONObject;

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
        ArrayList<BSUIRTeacher> teachers = getTeachersList();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.addBatch("TRUNCATE TABLE teachers_info");
            for (BSUIRTeacher teacher: teachers) {
                String data  = "(" +
                        teacher.getId() + ", '" +
                        teacher.getFirstName() + "', '" +
                        teacher.getLastName() + "', '" +
                        teacher.getMiddleName() + "', '" +
                        teacher.getRank() +"', '" +
                        teacher.getPhotoLink() + "', '" +
                        teacher.getFio() + "')";
                statement.addBatch("" +
                        "INSERT INTO teachers_info " +
                        "(id, first_name, last_name, middle_name, `rank`, photo_link, fio) " +
                        "VALUES " + data);
                statement.close();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroupsTable() {
        ArrayList<BSUIRGroup> groups = getGroupsList();
        String course;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.addBatch("TRUNCATE TABLE groups_table");
            for (BSUIRGroup group: groups) {
                course = group.getCourse() == -1 ? "null" : String.valueOf(group.getCourse());
                String data = "(" +
                        group.getId() + ", '" +
                        group.getName() + "', " +
                        course +
                        ")";
                statement.addBatch("" +
                        "INSERT INTO groups_table " +
                        "(id, name, course)" +
                        "VALUES " + data);
            }
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean containsGroup(String groupNumber) {
        boolean res = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM groups_table WHERE name='" + groupNumber + "'");
            int amount = 0;
            while (rs.next()) {
                amount++;
            }
            System.out.println(amount);
            res = amount == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<BSUIRTeacher> getTeachers() {
        ArrayList<BSUIRTeacher> teachers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM teachers_info");
            while (resultSet.next()) {
                teachers.add(new BSUIRTeacher(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("middle_name"),
                        resultSet.getString("rank"),
                        resultSet.getString("photo_link"),
                        resultSet.getInt("id"),
                        resultSet.getString("fio")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public ArrayList<BSUIRTeacher> getTeachersWithFIO(String fio) {
        ArrayList<BSUIRTeacher> teachers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery("" +
                            "SELECT * FROM teachers_info " +
                            "WHERE CONCAT(last_name, first_name, middle_name) " +
                            "LIKE '%" + fio.replaceAll("\\s", "") + "%'");
            while (resultSet.next()) {
                teachers.add(new BSUIRTeacher(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("middle_name"),
                        resultSet.getString("rank"),
                        resultSet.getString("photo_link"),
                        resultSet.getInt("id"),
                        resultSet.getString("fio")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    private ArrayList<BSUIRTeacher> getTeachersList() {
        JSONRequester requester = new JSONRequester();
        TeachersParser parser = new JSONParser(requester.getTeachers());
        return parser.parseTeachers();
    }

    private ArrayList<BSUIRGroup> getGroupsList() {
        JSONRequester requester = new JSONRequester();
        GroupsParser parser = new JSONParser(requester.getGroups());
        return parser.parseGroups();
    }


}
