package main.java.system;

import main.java.gui.controller.AuthorizationController;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
}
