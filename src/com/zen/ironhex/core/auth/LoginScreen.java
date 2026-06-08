package com.zen.ironhex.core.auth;

import com.zen.lib.terminal.drivers.LinuxTerminalDriver;
import com.zen.lib.terminal.input.TerminalInput;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

import static com.zen.lib.terminal.Terminal.*;

public class LoginScreen {
    public Pair<String, String> login() {
        LinuxTerminalDriver driver = new LinuxTerminalDriver();
        TerminalInput input = new TerminalInput(driver, false, 20);

        String username, password;
        int userLocation, passwordLocation;

        println();
        printCharacters('-', 30, true);

        userLocation = driver.getCursorY();
        println("+ USER ");

        passwordLocation = driver.getCursorY();
        println("+ PASS ");

        printCharacters('-', 30, true);

        driver.gotoxy(8, userLocation);
        username = input.read();

        driver.gotoxy(8, passwordLocation);

        input.setPassword(true);
        password = input.read();

        for (int i = userLocation - 1; i < userLocation + 5; i++) {
            driver.gotoxy(0, i);
            clearLine();
        }

        driver.gotoxy(0, userLocation - 1);
        return Pair.of(username, password);
    }
}
