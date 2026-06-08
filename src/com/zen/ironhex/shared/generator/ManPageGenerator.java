package com.zen.ironhex.shared.generator;

import java.util.Map;


public class ManPageGenerator {
    private String commandName;

    private String shortDescription;
    private String longDescription;

    private boolean hasArgs;
    private boolean hasEntity;

    private Map<String, String> arguments;

    private ManPageGenerator(Builder builder) {
        this.commandName = builder.commandName;

        this.shortDescription = builder.shortDescription;
        this.longDescription = builder.longDescription;

        this.hasArgs = builder.hasArgs;
        this.hasEntity = builder.hasEntity;

        this.arguments = builder.arguments;
    }

    public String generate() {
        StringBuilder builder = new StringBuilder();

        builder.append("\nNAME\n\t");
        builder.append("%s - %s\n\n".formatted(commandName, shortDescription));

        builder.append("SYNOPSIS\n");
        builder.append("\t%s  %s  %s".formatted(
                commandName,
                hasArgs ? "[args] ..." : "",
                hasEntity ? "[entity] ...\n\n" : "\n\n"
        ));

        builder.append("DESCRIPTION\n\t");
        builder.append(longDescription);

        if (arguments != null && !arguments.isEmpty()) {
            builder.append("\n\n");

            for (var entry : arguments.entrySet()) {
                builder.append("%s\n\t%s".formatted(entry.getKey(), entry.getValue()));
            }
        }

        builder.append("\n");
        return builder.toString();
    }

    public static class Builder {
        private String commandName;

        private String shortDescription;
        private String longDescription;

        private boolean hasArgs;
        private boolean hasEntity;

        private Map<String, String> arguments;

        public Builder commandName(String commandName) {
            this.commandName = commandName;
            return this;
        }

        public Builder shortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder longDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        public Builder hasArgs(boolean hasArgs) {
            this.hasArgs = hasArgs;
            return this;
        }

        public Builder hasEntity(boolean hasEntity) {
            this.hasEntity = hasEntity;
            return this;
        }

        public Builder arguments(Map<String, String> arguments) {
            this.arguments = arguments;
            return this;
        }

        public ManPageGenerator build() {
            return new ManPageGenerator(this);
        }
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setHasArgs(boolean hasArgs) {
        this.hasArgs = hasArgs;
    }

    public void setHasEntity(boolean hasEntity) {
        this.hasEntity = hasEntity;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }
}
