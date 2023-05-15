package main.java.system;

import java.sql.*;

public class DBConnection {
    private String url = "jdbc:postgresql://localhost:5432/tripsters";
    private String user = "postgres";
    private String password = "i16m17f21";
    private Connection connection;

    public DBConnection() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getNewStatement() throws SQLException {
        return connection.createStatement();
    }
}
