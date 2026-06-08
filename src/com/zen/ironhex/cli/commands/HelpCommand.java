package com.zen.ironhex.cli.commands;

import com.zen.ironhex.cli.commands.interfaces.Command;

import java.util.List;

import static com.zen.lib.terminal.Terminal.*;

public class HelpCommand implements Command {
    @Override
    public void execute(List<String> arguments) {
        try {
            if (arguments.isEmpty()) {
                println();

                println("add            -> create new entity");
                println("clear          -> clear terminal screen");
                println("help           -> show help about all commands");
                println("exit           -> terminate software");
                println("man            -> show command man page");

                println();
            } else {
                perror("error: command takes no arguments\n");
            }
        } catch (Exception ex) {
            perror("error: %s\n", ex.getMessage());
        }
    }
}
