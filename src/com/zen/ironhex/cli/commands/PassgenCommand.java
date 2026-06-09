package com.zen.ironhex.cli.commands;

import com.zen.ironhex.cli.commands.interfaces.Command;

import java.util.List;

import static com.zen.ironhex.shared.Variables.*;
import static com.zen.lib.terminal.Terminal.*;
import static com.zen.lib.terminal.commands.CommandUtils.*;

public class PassgenCommand implements Command {
    @Override
    public void execute(List<String> arguments) {
        try {

        } catch (Exception ex) {
            perror("error: %s\n", ex.getMessage());
        }
    }
}
