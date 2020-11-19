package com.traulko.project.dao;

import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.OrderItem;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;
import java.util.List;

/**
 * The {@code OrderItemDao} interface represents order item dao.
 *
 * @author Yan Traulko
 * @version 1.0
 */
public interface OrderItemDao {

    /**
     * Add order item.
     *
     * @param order the order
     * @param product the product
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(CustomOrder order, Product product, Connection connection) throws DaoException;

    /**
     * Remove order item.
     *
     * @param order the order
     * @param product the product
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(CustomOrder order, Product product, Connection connection) throws DaoException;

    /**
     * Remove all order items.
     *
     * @param orderId the order index
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean removeAll(Integer orderId, Connection connection) throws DaoException;

    /**
     * Find order items by order id.
     *
     * @param orderId the order index
     * @return the list of order items
     * @throws DaoException the dao exception
     */
    List<OrderItem> findOrderItemsByOrderId(Integer orderId) throws DaoException;
}
