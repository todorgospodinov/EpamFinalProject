package com.traulko.project.dao;

import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.DaoException;

import java.util.List;

public interface UserBasketProductDao {
    boolean add(UserBasketProduct userBasketProduct) throws DaoException;

    boolean remove(UserBasketProduct userBasketProduct) throws DaoException;

    List<UserBasketProduct> findBasketProductsByUserId(Integer id) throws DaoException;
}
