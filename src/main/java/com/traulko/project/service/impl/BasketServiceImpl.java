package com.traulko.project.service.impl;

import com.traulko.project.dao.BasketDao;
import com.traulko.project.dao.impl.BasketDaoImpl;
import com.traulko.project.entity.Basket;
import com.traulko.project.entity.Product;
import com.traulko.project.entity.User;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.BasketService;
import com.traulko.project.validator.ProjectValidator;

import java.util.List;

public class BasketServiceImpl implements BasketService {
    private BasketDao basketDao = new BasketDaoImpl();

    @Override
    public boolean add(Integer userId, String productId) throws ServiceException {
        boolean result = false;
        try {
            if (ProjectValidator.isCorrectIntValue(productId)) {
                Integer productIdValue = Integer.parseInt(productId);
                User user = new User();
                user.setUserId(userId);
                Product product = new Product();
                product.setProductId(productIdValue);
                Basket basket = new Basket();
                basket.setUser(user);
                basket.setProduct(product);
                result = basketDao.add(basket);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while adding basket", e);
        }
        return result;
    }

    @Override
    public boolean remove(Integer userId, String productId) throws ServiceException {
        boolean result = false;
        try {
            if (ProjectValidator.isCorrectIntValue(productId)) {
                Integer productIdValue = Integer.parseInt(productId);
                Product product = new Product();
                product.setProductId(productIdValue);
                User user = new User();
                user.setUserId(userId);
                Basket basket = new Basket();
                basket.setUser(user);
                basket.setProduct(product);
                result = basketDao.remove(basket);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while removing basket", e);
        }
        return result;
    }

    @Override
    public List<Basket> getBasketsByUserId(Integer id) throws ServiceException {
        try {
            return basketDao.getBasketsByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding baskets", e);
        }
    }
}