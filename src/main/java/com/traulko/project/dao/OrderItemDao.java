package com.traulko.project.dao;

import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.OrderItem;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface OrderItemDao {
    boolean add(CustomOrder order, Product product, Connection connection) throws DaoException;

    boolean remove(CustomOrder order, Product product, Connection connection) throws DaoException;

    boolean removeAll(Integer orderId, Connection connection) throws DaoException;

    List<OrderItem> findOrderItemsByOrderId(Integer id) throws DaoException;
}
