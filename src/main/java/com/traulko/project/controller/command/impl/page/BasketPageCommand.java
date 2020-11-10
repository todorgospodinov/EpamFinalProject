package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.RequestParameter;
import com.traulko.project.controller.command.CustomCommand;
import com.traulko.project.entity.Basket;
import com.traulko.project.entity.Product;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.BasketService;
import com.traulko.project.service.impl.BasketServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BasketPageCommand implements CustomCommand {
    private static final Logger LOGGER = LogManager.getLogger(BasketPageCommand.class);
    private static final BasketService basketService = new BasketServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        Integer userId = ((User) session.getAttribute(RequestParameter.USER)).getUserId();
        try {
            List<Basket> basketList = basketService.getBasketsByUserId(userId);
            request.setAttribute(RequestParameter.BASKETS, basketList);
            page = PagePath.BASKET;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Error while finding user basket", e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
