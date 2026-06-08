package com.zen.ironhex.cli.commands;

import com.zen.ironhex.cli.commands.interfaces.Command;

import java.util.List;

import static com.zen.lib.terminal.Terminal.*;

public class ExitCommand implements Command {

    @Override
    public void execute(List<String> arguments) {
        try {
            if (arguments.isEmpty()) {
                if (sure("Are you sure you want to exit", false)) {
                    System.exit(0);
                }
            } else {
                perror("error: command takes no arguments\n");
            }
        } catch (Exception ex) {
            perror("error: %s\n", ex.getMessage());
        }
    }
}
