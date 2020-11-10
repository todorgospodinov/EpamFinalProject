package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.BasketService;
import com.traulko.project.service.impl.BasketServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddProductToBasketCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final BasketService basketService = new BasketServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        Integer userId = ((User) session.getAttribute(RequestParameter.USER)).getUserId();
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        try {
            if (basketService.add(userId, productId)) {
                request.setAttribute(RequestParameter.ADD_PRODUCT_TO_BASKET_SUCCESS, true);
            } else {
                request.setAttribute(RequestParameter.ADD_PRODUCT_TO_BASKET_ERROR, true);
            }
            page = PagePath.MESSAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while adding product", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}