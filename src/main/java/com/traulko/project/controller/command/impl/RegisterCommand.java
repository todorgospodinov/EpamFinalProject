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

public class RegisterCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passwordRepeat = request.getParameter(RequestParameter.PASSWORD_REPEAT);
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String patronymic = request.getParameter(RequestParameter.PATRONYMIC);
        try {
            if (userService.add(email, password, passwordRepeat, name, surname, patronymic)) {
                User user = userService.findUserByEmail(email).get();
                userService.sendLetter(user, request.getRequestURL().toString());
                request.setAttribute(RequestParameter.USER_CONFIRM_REGISTRATION_LETTER, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestParameter.USER_DATA_INCORRECT, true);
                page = PagePath.REGISTRATION;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while register user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
