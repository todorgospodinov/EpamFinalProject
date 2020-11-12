package com.traulko.project.controller.command;

import com.traulko.project.controller.command.type.CommandType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CommandProvider {
    private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class);

    private CommandProvider() {
    }

    public static Optional <CustomCommand> defineCommand(String commandName) {
        Optional<CustomCommand> currentCommand;
        if (commandName != null && !commandName.isBlank()) {
            try {
                CommandType currentType = CommandType.valueOf(commandName.toUpperCase());
                currentCommand = Optional.of(currentType.getCommand());
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.ERROR, "Incorrect command type", commandName, e);
                currentCommand = Optional.empty();
            }
        } else {
            currentCommand = Optional.empty();
        }
        return currentCommand;
    }
}
