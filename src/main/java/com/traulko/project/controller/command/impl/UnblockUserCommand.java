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

public class UnblockUserCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(UnblockUserCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String email = request.getParameter(RequestParameter.EMAIL);
        try {
            if (userService.unblock(email)) {
                request.setAttribute(RequestParameter.USER_UNBLOCK_SUCCESS_MESSAGE, true);
            } else {
                request.setAttribute(RequestParameter.USER_UNBLOCK_ERROR_MESSAGE, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while unblocking user", e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
