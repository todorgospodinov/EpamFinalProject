package com.traulko.project.service;

import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    boolean add(Integer userId, List<UserBasketProduct> userBasketProductList) throws ServiceException;

    List<CustomOrder> findOrdersByUserId(Integer id) throws ServiceException;

    Optional<CustomOrder> findById(String id) throws ServiceException;
}
