package com.traulko.project.service;

import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.ServiceException;

import java.util.List;

public interface UserBasketProductService {

    boolean add(String userId, String productId) throws ServiceException;

    double calculateTotalPrice(List<UserBasketProduct> userBasketProductList);

    boolean remove(String userId, String productId) throws ServiceException;

    List<UserBasketProduct> getUserBasketProductsByUserId(String id) throws ServiceException;
}
