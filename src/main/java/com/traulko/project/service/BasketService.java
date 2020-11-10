package com.traulko.project.service;

import com.traulko.project.entity.Basket;
import com.traulko.project.exception.ServiceException;

import java.util.List;

public interface BasketService {

    boolean add(Integer userId, String productId) throws ServiceException;

    double calculateTotalPrice(List<Basket> basketList);

    boolean remove(Integer userId, String productId) throws ServiceException;

    List<Basket> getBasketsByUserId(Integer id) throws ServiceException;
}
