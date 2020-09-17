package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserService;
import com.traulko.project.service.impl.UserServiceImpl;
import com.traulko.project.validator.UserValidator;
import com.traulko.project.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();
        UserValidator userValidator = new UserValidatorImpl();
        String page;
        String registerLogin = request.getParameter(RequestParameter.REGISTER_LOGIN);
        String registerPassword = request.getParameter(RequestParameter.REGISTER_PASSWORD);
        String registerPasswordRepeat = request.getParameter(RequestParameter.REGISTER_PASSWORD_REPEAT);
        try {
            if (!userValidator.isLoginValid(registerLogin)) {
                request.setAttribute(RequestParameter.ERROR_REGISTER_MESSAGE,
                        "Login should be consist of letters, hyphens, underscores, 6-16 chars");
                page = PagePath.LOGIN;
            } else if (registerPassword == registerPasswordRepeat) {
                request.setAttribute(RequestParameter.ERROR_REGISTER_MESSAGE,
                        "Passwords are not similar");
                page = PagePath.LOGIN;
            } else if (!userValidator.isPasswordValid(registerPassword)) {
                request.setAttribute(RequestParameter.ERROR_REGISTER_MESSAGE,
                        "Password must contain at least one letter, at least one number, and be longer " +
                                "than six characters and less than sixteen.");
                page = PagePath.LOGIN;
            } else if (userService.isUserExists(registerLogin, registerPassword)) {
                request.setAttribute(RequestParameter.ERROR_REGISTER_MESSAGE,
                        "Your login should be unique");
                page = PagePath.LOGIN;
            } else {
                userService.registerUser(registerLogin, registerPassword, true);
                page = PagePath.MAIN;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while login user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
