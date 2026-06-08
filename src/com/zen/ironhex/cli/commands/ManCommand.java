package com.zen.ironhex.cli.commands;

import com.zen.ironhex.cli.commands.interfaces.Command;
import com.zen.ironhex.shared.generator.ManPageGenerator;

import java.util.List;

import static com.zen.lib.terminal.Terminal.*;

public class ManCommand implements Command {
    @Override
    public void execute(List<String> arguments) {
        try {
            if (arguments.isEmpty()) {
                perror("error: you need to specify a command\n");
            } else if (arguments.size() == 1) {
                String command = arguments.getFirst();

                switch (command) {
                    case "clear" -> {
                        ManPageGenerator generator = new ManPageGenerator.Builder()
                                .commandName("clear")
                                .shortDescription("clears terminal screen")
                                .longDescription("in some terminals like cmd.exe or early versions of powerhsell.exe, it may not work properly")
                                .hasArgs(false)
                                .hasEntity(false)
                                .build();

                        println(generator.generate());
                    }

                    case "exit" -> {
                        ManPageGenerator generator = new ManPageGenerator.Builder()
                                .commandName("exit")
                                .shortDescription("terminate software")
                                .longDescription("ask user for exit confirmation and then terminates the software")
                                .hasArgs(false)
                                .hasEntity(false)
                                .build();

                        println(generator.generate());
                    }

                    case "help" -> {
                        ManPageGenerator generator = new ManPageGenerator.Builder()
                                .commandName("help")
                                .shortDescription("show commands help")
                                .longDescription("print the list of all commands with single line help")
                                .hasArgs(false)
                                .hasEntity(false)
                                .build();

                        println(generator.generate());
                    }

                    case "man" -> {
                        ManPageGenerator generator = new ManPageGenerator.Builder()
                                .commandName("man")
                                .shortDescription("show command man page")
                                .longDescription("print command man page with all arguments and entities")
                                .hasArgs(true)
                                .hasEntity(false)
                                .build();

                        println(generator.generate());
                    }

                    case "add" -> {
                        ManPageGenerator generator = new ManPageGenerator.Builder()
                                .commandName("add")
                                .shortDescription("create new entity in the database")
                                .longDescription("you can add account, bankcard, contact, note and password")
                                .hasArgs(true)
                                .hasEntity(true)
                                .build();

                        println(generator.generate());
                    }

                    default -> {
                        perror("error: command \"%s\" has no man page.\n", command);
                    }
                }
            } else {
                perror("error: command takes only one argument\n");
            }
        } catch (Exception ex) {
            perror("error: %s\n", ex.getMessage());
        }
    }
}
