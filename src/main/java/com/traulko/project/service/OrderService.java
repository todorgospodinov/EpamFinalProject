package com.traulko.project.service;

import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    boolean add(String userId, List<UserBasketProduct> userBasketProductList) throws ServiceException;

    boolean remove(String orderId) throws ServiceException;

    List<CustomOrder> findOrdersByUserId(String id) throws ServiceException;

    List<CustomOrder> findBySearchQuery(String searchQuery) throws ServiceException;

    List<CustomOrder> findAll() throws ServiceException;

    boolean produce(String orderId) throws ServiceException;

    boolean reject(String orderId) throws ServiceException;

    Optional<CustomOrder> findById(String id) throws ServiceException;
}
