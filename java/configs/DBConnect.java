package configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";   
    private static final String PASSWORD = "";   

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}