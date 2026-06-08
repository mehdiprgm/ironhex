package com.zen.ironhex.cli.terminal;

import com.zen.ironhex.cli.commands.*;
import com.zen.ironhex.cli.commands.interfaces.Command;
import com.zen.lib.system.software.OS;
import com.zen.lib.terminal.drivers.LinuxTerminalDriver;
import com.zen.lib.terminal.input.TerminalInput;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zen.ironhex.shared.Variables.*;
import static com.zen.lib.terminal.Terminal.*;
import static com.zen.lib.terminal.commands.CommandUtils.*;

public class Prompt {
    private Map<String, Command> commands;

    public Prompt() {
        commands = Map.ofEntries(
                Map.entry("exit", new ExitCommand()),
                Map.entry("clear", new ClearCommand()),
                Map.entry("cls", new ClearCommand()),
                Map.entry("help", new HelpCommand()),
                Map.entry("man", new ManCommand()),
                Map.entry("add", new AddCommand())
        );
    }

    public void start() {
        TerminalInput input = new TerminalInput(new LinuxTerminalDriver(), false);

        while (true) {
            try {
                String commandInput = input.read("[%s # %s] ".formatted(vars.get("username"), OS.getHostName()));
                List<String> arguments = parseArgs(commandInput);

                if (!arguments.isEmpty()) {
                    String foundCommand = arguments.getFirst();
                    arguments.remove(foundCommand);

                    Command command = commands.get(foundCommand);
                    if (command != null) {
                        command.execute(arguments);
                    } else {
                        perror("error: command not found\n");
                    }
                }
            } catch (Exception ex) {
                perror("error: %s\n", ex.getMessage());
            }
        }
    }
}
