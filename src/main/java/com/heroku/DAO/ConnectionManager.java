package com.heroku.DAO;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection() throws SQLException {
        try {
            // Get the JDBC_DATABASE_URL from the environment variables
            String dbUrl = System.getenv("JDBC_DATABASE_URL");

            // Parse the URI to extract username and password
            URI dbUri = new URI(dbUrl.substring(5)); // Remove "jdbc:" prefix

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (URISyntaxException e) {
            throw new SQLException("Invalid JDBC_DATABASE_URL", e);
        }
    }
}
