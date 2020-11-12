package com.traulko.project.dao;

import com.traulko.project.entity.CustomOrder;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    boolean add(CustomOrder order, Connection connection) throws DaoException;

    List<CustomOrder> getOrdersByUserId(Integer id) throws DaoException;

    Optional<CustomOrder> findById(Integer id) throws DaoException;
}
