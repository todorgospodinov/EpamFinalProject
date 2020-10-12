package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.exception.SendMailException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserService;
import com.traulko.project.service.impl.UserServiceImpl;
import com.traulko.project.util.mail.MailSender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RegisterCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();
        String page;
        String email = request.getParameter(RequestParameter.REGISTER_EMAIL);
        String password = request.getParameter(RequestParameter.REGISTER_PASSWORD);
        String passwordRepeat = request.getParameter(RequestParameter.REGISTER_PASSWORD_REPEAT);
        String name = request.getParameter(RequestParameter.REGISTER_NAME);
        String surname = request.getParameter(RequestParameter.REGISTER_SURNAME);
        String patronymic = request.getParameter(RequestParameter.REGISTER_PATRONYMIC);
        try {
            if (!userService.isEmailValid(email)) {
                request.setAttribute(RequestParameter.ERROR_REGISTER_MESSAGE,
                        "Incorrect Email address");
                page = PagePath.LOGIN;
            } else if (!password.equals(passwordRepeat)) {
                request.setAttribute(RequestParameter.ERROR_REGISTER_MESSAGE,
                        "Passwords are not similar");
                page = PagePath.LOGIN;
            } else if (!userService.isPasswordValid(password)) {
                request.setAttribute(RequestParameter.ERROR_REGISTER_MESSAGE,
                        "Password must contain at least one letter, at least one number, and be longer " +
                                "than six characters and less than sixteen.");
                page = PagePath.LOGIN;
            } else if (userService.isEmailFree(email)) {
                request.setAttribute(RequestParameter.ERROR_REGISTER_MESSAGE,
                        "Your email should be unique");
                page = PagePath.LOGIN;
            } else {
                MailSender sender = new MailSender(email, RequestParameter.MAIL_MESSAGE,
                        RequestParameter.MAIL_MESSAGE_LINK + email); // TODO: 11.10.2020 email -> token
                sender.send();
                userService.registerUser(email, password, name, surname, patronymic);
                page = PagePath.MAIN;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while register user", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        } catch (IOException | SendMailException e) {
            LOGGER.log(Level.ERROR, "Error while send message", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
