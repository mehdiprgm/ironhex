package com.zen.ironhex.cli.parser;

import com.zen.ironhex.shared.Result;

import java.util.List;

public class EntityResolver {
    public Result searchEntity(List<String> arguments, boolean removeEntity) {
        List<String> entities = List.of("--account", "--card", "--contact", "--note", "--pass");

        String foundEntity = null;
        int countEntity = 0;

        for (String argument : arguments) {
            if (argument.startsWith("--")) {
                if (entities.contains(argument)) {
                    countEntity++;
                    foundEntity = argument;
                } else {
                    /* The entity is not valid */
                    return new Result(false, "%s is not valid".formatted(argument));
                }
            }
        }

        /* Only one entity can be used, not more */
        if (countEntity > 1) {
            return new Result(false, "Only one entity can be used at the same time");
        }

        if (removeEntity && foundEntity != null) {
            arguments.remove(foundEntity);
        }

        return new Result(true, foundEntity);
    }
}
