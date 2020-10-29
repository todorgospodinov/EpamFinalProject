package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.controller.command.impl.FindUsersCommand;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserService;
import com.traulko.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(AdminPageCommand.class);
    private static final UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            List<User> userList = userService.findAll();
            request.setAttribute(RequestParameter.USERS, userList);
            page = PagePath.ADMIN;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding all users", e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
