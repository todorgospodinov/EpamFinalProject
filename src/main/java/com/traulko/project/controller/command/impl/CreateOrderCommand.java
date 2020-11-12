package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.BasketService;
import com.traulko.project.service.OrderService;
import com.traulko.project.service.impl.UserBasketProductServiceImpl;
import com.traulko.project.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CreateOrderCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(CreateOrderCommand.class);
    private static final OrderService orderService = new OrderServiceImpl();
    private static final BasketService basketService = new UserBasketProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        Integer userId = ((User) session.getAttribute(RequestParameter.USER)).getUserId();
        try {
            List<UserBasketProduct> userBasketProductList = basketService.getUserBasketProductsByUserId(userId);
            if (orderService.add(userId, userBasketProductList)) {
                request.setAttribute(RequestParameter.ORDER_CREATE_SUCCESS_MESSAGE, true);
            } else {
                request.setAttribute(RequestParameter.ORDER_CREATE_ERROR_MESSAGE, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while creating order", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
