package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderService;
import com.traulko.project.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PersonalAccountPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(PersonalAccountPageCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(RequestParameter.USER_ID);
        try {
            List<CustomOrder> orderList = orderService.findOrdersByUserId(userId);
            request.setAttribute(RequestParameter.ORDERS, orderList);
            page = PagePath.PERSONAL_ACCOUNT;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding orders", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
