package com.zen.ironhex.core;


import com.zen.ironhex.domain.database.Database;
import com.zen.ironhex.shared.generator.PathProvider;
import com.zen.ironhex.shared.Result;
import com.zen.lib.files.FileManager;

import java.io.IOException;
import java.sql.SQLException;

public class BootLoader {
    public Result checkMainDirectory() {
        try {
            String mainDirPath = PathProvider.getMainDirectoryPath();

            if (FileManager.exists(mainDirPath, FileManager.FileType.DIRECTORY)) {
                return new Result(true, "Everything is ok");
            }

            if (FileManager.mkdir(mainDirPath, false)) {
                return new Result(true, "Main directory created successfully");
            }

            return new Result(false, "Failed to create the main directory");
        } catch (IOException ex) {
            return new Result(false, ex.getMessage());
        }
    }

    public Result checkDatabaseFile() {
        try {
            String databasePath = PathProvider.getDatabasePath();
            Database database = new Database();

            if (!FileManager.exists(databasePath, FileManager.FileType.FILE)) {
                if (!database.create().success()) {
                    return new Result(false, "Failed to create database file");
                }
            }

            if (database.createTables().success()) {
                return new Result(true, "Everything is ok");
            }

            return new Result(false, "Failed to create database tables");
        } catch (SQLException ex) {
            return new Result(false, ex.getMessage());
        }
    }
}
