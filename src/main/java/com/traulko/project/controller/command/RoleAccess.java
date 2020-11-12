package com.traulko.project.controller.command;

import com.traulko.project.controller.command.type.CommandType;

import java.util.EnumSet;
import java.util.Set;

public enum RoleAccess {
    GUEST(EnumSet.of(CommandType.FIND_PRODUCTS_COMMAND,
            CommandType.LOGIN_COMMAND,
            CommandType.REGISTER_COMMAND,
            CommandType.SWITCH_LANGUAGE_COMMAND,
            CommandType.FORGOT_PASSWORD_COMMAND,
            CommandType.CATALOG_PAGE,
            CommandType.LOGIN_PAGE,
            CommandType.FORGOT_PASSWORD_PAGE,
            CommandType.MAIN_PAGE,
            CommandType.PRODUCT_PAGE,
            CommandType.REGISTRATION_PAGE)),

    USER(EnumSet.of(CommandType.ACCOUNT_ACCESS_COMMAND,
            CommandType.ADD_PRODUCT_TO_BASKET_COMMAND,
            CommandType.CHANGE_PASSWORD_COMMAND,
            CommandType.CREATE_ORDER_COMMAND,
            CommandType.DELETE_PRODUCT_FROM_BASKET_COMMAND,
            CommandType.FILL_UP_BALANCE_COMMAND,
            CommandType.FIND_PRODUCTS_COMMAND,
            CommandType.SWITCH_LANGUAGE_COMMAND,
            CommandType.LOGOUT_COMMAND,
            CommandType.BASKET_PAGE,
            CommandType.CATALOG_PAGE,
            CommandType.CHANGE_PASSWORD_PAGE,
            CommandType.FILL_UP_BALANCE_PAGE,
            CommandType.MAIN_PAGE,
            CommandType.ORDER_PAGE,
            CommandType.PERSONAL_ACCOUNT_PAGE,
            CommandType.PRODUCT_PAGE)),

    ADMIN(EnumSet.of(CommandType.ACCOUNT_ACCESS_COMMAND,
            CommandType.ADD_PRODUCT_COMMAND,
            CommandType.BLOCK_USER_COMMAND,
            CommandType.CHANGE_PASSWORD_COMMAND,
            CommandType.DELETE_USER_COMMAND,
            CommandType.FIND_PRODUCTS_COMMAND,
            CommandType.FIND_USERS_COMMAND,
            CommandType.SWITCH_LANGUAGE_COMMAND,
            CommandType.UNBLOCK_USER_COMMAND,
            CommandType.LOGOUT_COMMAND,
            CommandType.ADD_PRODUCT_PAGE,
            CommandType.ADMIN_PAGE,
            CommandType.CATALOG_PAGE,
            CommandType.CHANGE_PASSWORD_PAGE,
            CommandType.MAIN_PAGE,
            CommandType.ORDER_PAGE,
            CommandType.PERSONAL_ACCOUNT_PAGE,
            CommandType.PRODUCT_PAGE));

    private Set<CommandType> accessCommands;

    RoleAccess(Set<CommandType> accessCommands) {
        this.accessCommands = accessCommands;
    }

    public Set<CommandType> getAccessCommands() {
        return this.accessCommands;
    }
}
