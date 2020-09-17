package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserService;
import com.traulko.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public String execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();
        String page;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            if (userService.isUserExists(login, password)) {
                page = PagePath.MAIN;
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
