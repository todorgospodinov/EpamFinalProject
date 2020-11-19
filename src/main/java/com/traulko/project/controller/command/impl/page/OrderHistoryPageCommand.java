package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderService;
import com.traulko.project.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code OrderHistoryPageCommand} class represents browse order history page command.
 *
 * @author Yan Traulko
 * @version 1.0
 */
public class OrderHistoryPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(OrderHistoryPageCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String userId = request.getParameter(RequestParameter.USER_ID);
        try {
            List<CustomOrder> orderList = orderService.findOrdersByUserId(userId);
            request.setAttribute(RequestParameter.ORDERS, orderList);
            page = PagePath.ORDER_HISTORY;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding orders", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
