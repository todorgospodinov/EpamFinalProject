package com.traulko.project.controller.command;

import com.traulko.project.controller.command.type.CommandType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final CustomCommand MAIN_PAGE_COMMAND = CommandType.MAIN_PAGE.getCommand();

    private CommandProvider() {
    }

    public static CustomCommand defineCommand(String commandName) {
        CustomCommand currentCommand;
        if (commandName != null && !commandName.isBlank()) {
            try {
                CommandType currentType = CommandType.valueOf(commandName.toUpperCase());
                currentCommand = currentType.getCommand();
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.ERROR, "Incorrect command type: {}", commandName, e);
                currentCommand = MAIN_PAGE_COMMAND;
            }
        } else {
            currentCommand = MAIN_PAGE_COMMAND;
        }
        return currentCommand;
    }
}
