package com.taskmanager.storage.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:task-manager.db";

    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        init(connection);
        return connection;
    }

    private static void init(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {

            stmt.execute(
                "CREATE TABLE IF NOT EXISTS tasks (" +
                "id TEXT PRIMARY KEY," +
                "title TEXT," +
                "status TEXT," +
                "updated_at INTEGER," +
                "sync_state TEXT" +
                ")"
            );
        }
    }
}
