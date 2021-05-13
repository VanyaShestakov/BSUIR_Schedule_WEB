package Databases;

import Schedule.BSUIRTeacher;
import Tools.Pair;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MySQLQueryExecutor implements QueryExecutor {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private final String dbName;
    private final String user;
    private final String password;

    public MySQLQueryExecutor(String dbName, String user, String password) {
        try {
            java.sql.Driver driver = new Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }

    public void insert(String tableName, List<Pair<String, ?>> data) {
        try (Connection connection = DriverManager.getConnection(URL + dbName, user, password)) {
            Statement statement = connection.createStatement();
            StringJoiner fieldNames = new StringJoiner(", ", "(" , ")");
            StringJoiner values = new StringJoiner(", ", "(" , ")");
            for (Pair<String, ?> pair: data) {
                fieldNames.add(pair.getKey());
                if (pair.getValue() instanceof Number) {
                    values.add((pair.getValue()).toString());
                } else if (pair.getValue() instanceof String) {
                    values.add("'" + pair.getValue() + "'");
                }
            }
            statement.execute("INSERT INTO " + tableName + " " + fieldNames + "VALUES " + values);
            System.out.println(fieldNames);
            System.out.println(values);
            //statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<Pair<String, ?>> list = new ArrayList<>();
        list.add(new Pair<>("id", 3));
        list.add(new Pair<>("name", "Ivan"));
        list.add(new Pair<>("dick_size", 3.5));
        MySQLQueryExecutor executor = new MySQLQueryExecutor("teachers_db", "admin", "admin");
        executor.insert("someTable", list);
    }

}
