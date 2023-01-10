package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public Connection databaseLink;

    public Connection getDBConnection() {

        String databaseName = "students";
        String databaseUser = "students";
        String databasePassword = "stud1234!";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return databaseLink;
    }

}
