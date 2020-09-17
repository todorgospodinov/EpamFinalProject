package com.traulko.project.controller.command.type;

import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.controller.command.impl.LoginCommand;
import com.traulko.project.controller.command.impl.LogoutCommand;
import com.traulko.project.controller.command.impl.RegisterCommand;

public enum CommandType {
    LOGIN_COMMAND(new LoginCommand()),
    REGISTER_COMMAND(new RegisterCommand()),
    LOGOUT_COMMAND(new LogoutCommand());

    private final CustomCommand command;

    CommandType(CustomCommand command) {
        this.command = command;
    }

    public CustomCommand getCommand() {
        return command;
    }
}
