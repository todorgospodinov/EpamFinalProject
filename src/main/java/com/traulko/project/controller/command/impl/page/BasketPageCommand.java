package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.BasketService;
import com.traulko.project.service.impl.UserBasketProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BasketPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(BasketPageCommand.class);
    private static final BasketService basketService = new UserBasketProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        Integer userId = ((User) session.getAttribute(RequestParameter.USER)).getUserId();
        try {
            List<UserBasketProduct> userBasketProductList = basketService.getUserBasketProductsByUserId(userId);
            double totalPrice = basketService.calculateTotalPrice(userBasketProductList);
            request.setAttribute(RequestParameter.TOTAL_PRICE, totalPrice);
            request.setAttribute(RequestParameter.BASKETS, userBasketProductList);
            page = PagePath.BASKET;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding user basket", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
