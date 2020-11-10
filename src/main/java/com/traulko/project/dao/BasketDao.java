package com.traulko.project.dao;

import com.traulko.project.entity.Basket;
import com.traulko.project.exception.DaoException;

import java.util.List;

public interface BasketDao {
    boolean add(Basket basket) throws DaoException;

    boolean remove(Basket basket) throws DaoException;

    List<Basket> getBasketsByUserId(Integer id) throws DaoException;
}
