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
import java.util.List;

public class FindUsersCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(FindUsersCommand.class);
    private static final UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String searchQuery = request.getParameter(RequestParameter.SEARCH_USERS_QUERY);
        try {
            List<User> userList = userService.findBySearchQuery(searchQuery);
            request.setAttribute(RequestParameter.USERS, userList);
            page = PagePath.ADMIN_USERS_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding users", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
