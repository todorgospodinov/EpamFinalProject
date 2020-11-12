package com.traulko.project.service.impl;

import com.traulko.project.dao.UserBasketProductDao;
import com.traulko.project.dao.impl.UserBasketProductDaoImpl;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.entity.Product;
import com.traulko.project.entity.User;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.BasketService;
import com.traulko.project.validator.ProjectValidator;

import java.util.List;

public class UserBasketProductServiceImpl implements BasketService {
    private UserBasketProductDao userBasketProductDao = new UserBasketProductDaoImpl();

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
                UserBasketProduct userBasketProduct = new UserBasketProduct();
                userBasketProduct.setUser(user);
                userBasketProduct.setProduct(product);
                result = userBasketProductDao.add(userBasketProduct);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while adding basket", e);
        }
        return result;
    }

    @Override
    public double calculateTotalPrice(List<UserBasketProduct> userBasketProductList) {
        double totalPrice = 0;
        for (UserBasketProduct userBasketProduct : userBasketProductList) {
            totalPrice += userBasketProduct.getProduct().getPrice();
        }
        return totalPrice;
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
                UserBasketProduct userBasketProduct = new UserBasketProduct();
                userBasketProduct.setUser(user);
                userBasketProduct.setProduct(product);
                result = userBasketProductDao.remove(userBasketProduct);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while removing basket", e);
        }
        return result;
    }

    @Override
    public List<UserBasketProduct> getUserBasketProductsByUserId(Integer id) throws ServiceException {
        try {
            return userBasketProductDao.getBasketProductsByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding baskets", e);
        }
    }
}