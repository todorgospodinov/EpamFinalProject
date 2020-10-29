package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserService;
import com.traulko.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements CustomCommand {
    private static final String ATTRIBUTE_USER = "user";
    private static final UserService userService = new UserServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request) {
        String page;
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            if (userService.isUserExists(email, password)) {
                User user = userService.getUserByEmail(email);
                User.Status status = user.getStatus();
                switch (status) {
                    case ENABLE -> {
                        request.getSession().setAttribute(ATTRIBUTE_USER, user);
                        if (user.getRole() == User.Role.ADMIN) {
                            // ADMIN
                            // TODO: 22.09.2020  
                            page = PagePath.MAIN;
                        } else {
                            // USER
                            // TODO: 22.09.2020
                            page = PagePath.MAIN;
                        }
                    }
                    case BLOCKED -> {
                        request.setAttribute(RequestParameter.ERROR_LOGIN_PASSWORD_MESSAGE,
                                "Account is blocked");
                        page = PagePath.LOGIN;
                    }
                    case NOT_CONFIRMED -> {
                        request.setAttribute(RequestParameter.ERROR_LOGIN_PASSWORD_MESSAGE,
                                "Account is not confirmed. Check email.");
                        page = PagePath.LOGIN;
                    }
                    default -> {
                        page = PagePath.ERROR;
                        // TODO: 22.09.2020  
                    }
                }
            } else {
                request.setAttribute(RequestParameter.ERROR_LOGIN_PASSWORD_MESSAGE,
                        "Incorrect login or password");
                page = PagePath.LOGIN;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while login user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
