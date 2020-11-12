package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.OrderItem;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderItemService;
import com.traulko.project.service.OrderService;
import com.traulko.project.service.impl.OrderItemServiceImpl;
import com.traulko.project.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class OrderPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(OrderPageCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();
    private static final OrderItemService orderItemService = new OrderItemServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        try {
            Optional<CustomOrder> orderOptional = orderService.findById(orderId);
            if (orderOptional.isPresent()) {
                List<OrderItem> orderItemList = orderItemService.findOrderItemsByOrderId(orderId);
                request.setAttribute(RequestParameter.ORDER_ITEMS, orderItemList);
                request.setAttribute(RequestParameter.ORDER, orderOptional.get());
                // TODO: 12.11.2020
            }
            page = PagePath.ORDER;
            //page = PagePath.PERSONAL_ACCOUNT;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding users", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
