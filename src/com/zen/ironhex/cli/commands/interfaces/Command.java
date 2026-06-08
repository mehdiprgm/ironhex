package com.zen.ironhex.cli.commands.interfaces;

import java.util.List;

public interface Command {
    void execute(List<String> arguments);
}
