package com.traulko.project.controller.command.type;

import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.controller.command.impl.*;
import com.traulko.project.controller.command.impl.page.*;

public enum CommandType {
    LOGIN_COMMAND(new LoginCommand()),
    REGISTER_COMMAND(new RegisterCommand()),
    LOGOUT_COMMAND(new LogoutCommand()),
    ACTIVATE_ACCOUNT(new ActivateAccountCommand()),
    FIND_USERS_COMMAND(new FindUsersCommand()),


    SWITCH_LANGUAGE(new SwitchLanguageCommand()),


    MAIN_PAGE(new MainPageCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    PERSONAL_ACCOUNT_PAGE(new PersonalAccountPageCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand());

    private final CustomCommand command;

    CommandType(CustomCommand command) {
        this.command = command;
    }

    public CustomCommand getCommand() {
        return command;
    }
}
