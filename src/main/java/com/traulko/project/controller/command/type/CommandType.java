package com.traulko.project.controller.command.type;

import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.controller.command.impl.*;
import com.traulko.project.controller.command.impl.transition.GoToLoginPageCommand;
import com.traulko.project.controller.command.impl.transition.GoToMainPageCommand;
import com.traulko.project.controller.command.impl.transition.GoToRegistrationPageCommand;

public enum CommandType {
    LOGIN_COMMAND(new LoginCommand()),
    REGISTER_COMMAND(new RegisterCommand()),
    LOGOUT_COMMAND(new LogoutCommand()),
    TEST_COMMAND(new TestCommand()),
    ACTIVATE_ACCOUNT(new ActivateAccountCommand()),


    GO_TO_MAIN_PAGE(new GoToMainPageCommand()),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand()),
    GO_TO_REGISTRATION_PAGE(new GoToRegistrationPageCommand());

    private final CustomCommand command;

    CommandType(CustomCommand command) {
        this.command = command;
    }

    public CustomCommand getCommand() {
        return command;
    }
}
