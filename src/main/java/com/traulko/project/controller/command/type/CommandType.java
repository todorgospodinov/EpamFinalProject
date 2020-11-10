package com.traulko.project.controller.command.type;

import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.controller.command.impl.*;
import com.traulko.project.controller.command.impl.page.*;

public enum CommandType {
    LOGIN_COMMAND(new LoginCommand()),
    REGISTER_COMMAND(new RegisterCommand()),
    LOGOUT_COMMAND(new LogoutCommand()),
    ACCOUNT_ACCESS_COMMAND(new AccountAccessCommand()),
    FIND_USERS_COMMAND(new FindUsersCommand()),
    DELETE_USER_COMMAND(new DeleteUserCommand()),
    BLOCK_USER_COMMAND(new BlockUserCommand()),
    UNBLOCK_USER_COMMAND(new UnblockUserCommand()),
    ADD_PRODUCT_COMMAND(new AddProductCommand()),
    FORGOT_PASSWORD_COMMAND(new ForgotPasswordCommand()),
    CHANGE_PASSWORD_COMMAND(new ChangePasswordCommand()),
    ADD_PRODUCT_TO_BASKET_COMMAND(new AddProductToBasketCommand()),
    DELETE_PRODUCT_FROM_BASKET_COMMAND(new DeleteProductFromBasketCommand()),


    SWITCH_LANGUAGE_COMMAND(new SwitchLanguageCommand()),


    MAIN_PAGE(new MainPageCommand()),
    PRODUCT_PAGE(new ProductPageCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    PERSONAL_ACCOUNT_PAGE(new PersonalAccountPageCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    CATALOG_PAGE(new CatalogPageCommand()),
    FORGOT_PASSWORD_PAGE(new ForgotPasswordPageCommand()),
    CHANGE_PASSWORD_PAGE(new ChangePasswordPageCommand()),
    BASKET_PAGE(new BasketPageCommand()),
    ADD_PRODUCT_PAGE(new AddProductPageCommand());

    private final CustomCommand command;

    CommandType(CustomCommand command) {
        this.command = command;
    }

    public CustomCommand getCommand() {
        return command;
    }
}
