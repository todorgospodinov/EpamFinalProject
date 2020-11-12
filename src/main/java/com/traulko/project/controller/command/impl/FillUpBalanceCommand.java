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
import javax.servlet.http.HttpSession;

public class FillUpBalanceCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(FillUpBalanceCommand.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(RequestParameter.USER_ID);
        String moneyAmount = request.getParameter(RequestParameter.MONEY_AMOUNT);
        try {
            if (userService.fillUpBalance(userId, moneyAmount)) {
                request.setAttribute(RequestParameter.FILL_UP_BALANCE_SUCCESS, true);
                page = PagePath.MESSAGE;
            } else {
                request.setAttribute(RequestParameter.INCORRECT_MONEY_AMOUNT, true);
                page = PagePath.FILL_UP_BALANCE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while filling up balance", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
