package com.zen.ironhex;

import com.zen.ironhex.cli.terminal.Prompt;
import com.zen.ironhex.core.BootLoader;
import com.zen.ironhex.core.auth.LoginScreen;
import com.zen.ironhex.core.auth.UserAuthentication;
import com.zen.ironhex.shared.Result;
import com.zen.lib.notification.Notification;
import com.zen.lib.notification.driver.LinuxNotificationDriver;
import com.zen.lib.notification.enums.NotificationPriority;
import com.zen.lib.system.software.OS;
import com.zen.lib.terminal.drivers.LinuxTerminalDriver;
import com.zen.lib.terminal.input.TerminalInput;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zen.lib.terminal.Terminal.*;
import static com.zen.lib.terminal.commands.CommandUtils.findArgument;

public class Application {
    static void main(String[] args) {
        startEngine(args);
    }

    private static void startEngine(String[] args) {
        /* Initializing variables */
        BootLoader bootLoader = new BootLoader();
        UserAuthentication auth = new UserAuthentication();

        List<String> arguments = new ArrayList<>(Arrays.asList(args));

        LinuxTerminalDriver driver = new LinuxTerminalDriver();
        TerminalInput input = new TerminalInput(driver, true, 20);

        String username, password, reEnterPassword;

        clearScreen();
        println();

        /* Start booting the system */
        print("Checking main directory path         ");
        checkResult(bootLoader.checkMainDirectory());

        print("Checking database file               ");
        checkResult(bootLoader.checkDatabaseFile());

        /* Check for register mode */
        if (findArgument(arguments, "-r", false) != null) {
            username = findArgument(arguments, "-a");

            if (username == null) {
                perror("\nerror: no username found\n");
                System.exit(0);
            }

            password = input.read("\npassword: ");

            if (password.isEmpty()) {
                System.exit(0);
            }

            reEnterPassword = input.read("Re-enter password: ");
            if (password.equals(reEnterPassword)) {
                Result result = auth.register(username, password);

                if (result.success()) {
                    println(result.message());
                } else {
                    perror("\nerror: %s\n", result.message());
                }
            } else {
                perror("\nerror: passwords are not same (mismatch)\n");
            }
        } else {
            Pair<String, String> pair = new LoginScreen().login();

            username = pair.getKey();
            password = pair.getValue();

            if (!username.isEmpty() && !password.isEmpty()) {
                Result result = auth.login(username, password);

                if (result.success()) {
                    try {
                        Notification notification = new Notification(
                                "IRONHEX",
                                "Welcome %s".formatted(username),
                                "You can use ironhex",
                                "",
                                NotificationPriority.HIGH
                        );

                        new LinuxNotificationDriver().show(notification);
                    } catch (Exception ex) {
                        perror("error: %s\n", ex.getMessage());
                    }

                    printInformation();
                    new Prompt().start();
                } else {
                    perror("\nerror: %s\n", result.message());
                }
            }
        }
    }

    private static void checkResult(Result result) {
        print("[");

        if (result.success()) {
            printMessage(MessageType.SUCCESS, "OK");
            println("]");
        } else {
            printMessage(MessageType.ERROR, "FAILED");
            println("]");

            printf("\nerror: %s", result.message());
            System.exit(0);
        }
    }

    private static void printInformation() {
        print("\n+ ");

        printCharacters('-', 20, false);
        print(" IRONHEX ");
        printCharacters('-', 20, false);
        println(" +");

        println("+ version      2.0");
        printf("+ kernel       %s\n\n", OS.getKernelModel());
        printf("+ login        %s\n", OS.getTimeDate("EEE MMM d HH:mm:ss yyyy"));

        print("+ ");
        printCharacters('-', 49, false);
        println(" +");

        println();
    }
}
