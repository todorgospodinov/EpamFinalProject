package com.traulko.project.dao;

import com.traulko.project.entity.CustomOrder;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    boolean add(CustomOrder order, Connection connection) throws DaoException;

    boolean remove(Integer orderId, Connection connection) throws DaoException;

    List<CustomOrder> findBySearchQuery(String searchQuery) throws DaoException;

    boolean produce(Integer orderId, LocalDate date) throws DaoException;

    boolean reject(Integer orderId, LocalDate date) throws DaoException;

    List<CustomOrder> findOrdersByUserId(Integer id) throws DaoException;

    List<CustomOrder> findAll() throws DaoException;

    Optional<CustomOrder> findById(Integer id) throws DaoException;
}
