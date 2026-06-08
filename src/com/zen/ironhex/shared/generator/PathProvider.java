package com.zen.ironhex.shared.generator;

import com.zen.lib.system.software.SystemUser;

public class PathProvider {
    private final static String MAIN_DIR;

    static {
        MAIN_DIR = "%s/.local/share/%s".formatted(SystemUser.getHomeDirectory(), "ironhex2");
    }

    public static String getMainDirectoryPath() {
        return MAIN_DIR;
    }

    public static String getDatabasePath() {
        return "%s/%s".formatted(MAIN_DIR, "ironhex.db");
    }
}
