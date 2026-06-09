package com.zen.ironhex.domain.database;

import com.zen.ironhex.shared.generator.PathProvider;
import com.zen.ironhex.shared.Result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String ENABLE_FOREIGN_KEYS = """
            PRAGMA foreign_keys = ON;
            """;

    private static final String CREATE_USERS_TABLE = """
            CREATE TABLE IF NOT EXISTS Users (
                id INTEGER NOT NULL PRIMARY KEY,
                username TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                salt TEXT NOT NULL,
                isAdmin INTEGER NOT NULL,
                lastLoginDate TEXT,
                createDate TEXT NOT NULL
            );
            """;

    private static final String CREATE_ACCOUNTS_TABLE = """
            CREATE TABLE IF NOT EXISTS Accounts (
                id INTEGER NOT NULL PRIMARY KEY,
                userId INTEGER NOT NULL,
                name TEXT NOT NULL,
                username TEXT NOT NULL,
                password TEXT NOT NULL,
                extraInformation TEXT,
                createDate TEXT NOT NULL,
                CONSTRAINT FK_Accounts_userId FOREIGN KEY (userId) REFERENCES Users(id)
            );
            """;

    private static final String CREATE_BANKCARDS_TABLE = """
            CREATE TABLE IF NOT EXISTS Bankcards (
                id INTEGER NOT NULL PRIMARY KEY,
                userId INTEGER NOT NULL,
                name TEXT NOT NULL,
                cardNumber TEXT NOT NULL,
                accountNumber TEXT,
                cvv2 TEXT NOT NULL,
                expireDate TEXT NOT NULL,
                password TEXT NOT NULL,
                createDate TEXT NOT NULL,
                CONSTRAINT FK_Bankcards_userId FOREIGN KEY (userId) REFERENCES Users(id)
            );
            """;

    private static final String CREATE_CONTACTS_TABLE = """
            CREATE TABLE IF NOT EXISTS Contacts (
                id INTEGER NOT NULL PRIMARY KEY,
                userId INTEGER NOT NULL,
                name TEXT NOT NULL,
                phoneNumber TEXT NOT NULL,
                extraInformation TEXT,
                createDate TEXT NOT NULL,
                CONSTRAINT FK_Contacts_userId FOREIGN KEY (userId) REFERENCES Users(id)
            );
            """;

    private static final String CREATE_LOGS_TABLE = """
            CREATE TABLE IF NOT EXISTS Logs (
                id INTEGER NOT NULL PRIMARY KEY,
                userId INTEGER NOT NULL,
                message TEXT NOT NULL,
                type INTEGER NOT NULL,
                module INTEGER NOT NULL,
                date TEXT NOT NULL,
                time TEXT NOT NULL,
                CONSTRAINT FK_Logs_userId FOREIGN KEY (userId) REFERENCES Users(id)
            );
            """;

    private static final String CREATE_NOTES_TABLE = """
            CREATE TABLE IF NOT EXISTS Notes (
                id INTEGER NOT NULL PRIMARY KEY,
                userId INTEGER NOT NULL,
                name TEXT NOT NULL,
                content TEXT,
                createDate TEXT NOT NULL,
                CONSTRAINT FK_Notes_userId FOREIGN KEY (userId) REFERENCES Users(id)
            );
            """;

    private static final String CREATE_PASSWORDS_TABLE = """
            CREATE TABLE IF NOT EXISTS Passwords (
                id INTEGER NOT NULL PRIMARY KEY,
                userId INTEGER NOT NULL,
                name text NOT NULL,
                password TEXT NOT NULL,
                length TEXT NOT NULL,
                strength INTEGER NOT NULL,
                createDate TEXT NOT NULL,
                CONSTRAINT FK_Passwords_userId FOREIGN KEY (userId) REFERENCES Users(id)
            );
            """;

    public String generateDatabaseURL() {
        return "jdbc:sqlite:%s".formatted(PathProvider.getDatabasePath());
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(generateDatabaseURL());
    }

    public Result createTables() throws SQLException {
        try (Connection conn = connect(); Statement statement = conn.createStatement()) {
            statement.execute(ENABLE_FOREIGN_KEYS);
            statement.execute(CREATE_USERS_TABLE);
            statement.execute(CREATE_ACCOUNTS_TABLE);
            statement.execute(CREATE_BANKCARDS_TABLE);
            statement.execute(CREATE_CONTACTS_TABLE);
            statement.execute(CREATE_LOGS_TABLE);
            statement.execute(CREATE_NOTES_TABLE);
            statement.execute(CREATE_PASSWORDS_TABLE);

            return new Result(true, "Tables created successfully");
        } catch (SQLException ex) {
            return new Result(false, ex.getMessage());
        }
    }

    public Result create() throws SQLException {
        try (Connection connection = DriverManager.getConnection(generateDatabaseURL())) {
            if (connection == null) {
                return new Result(false, "Failed to create new database");
            }

            connection.close();
            return new Result(true, "Database created successfully");
        }
    }
}
