package com.traulko.project.service;

import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.ServiceException;

import java.util.List;

public interface BasketService {

    boolean add(Integer userId, String productId) throws ServiceException;

    double calculateTotalPrice(List<UserBasketProduct> userBasketProductList);

    boolean remove(Integer userId, String productId) throws ServiceException;

    List<UserBasketProduct> getUserBasketProductsByUserId(Integer id) throws ServiceException;
}
