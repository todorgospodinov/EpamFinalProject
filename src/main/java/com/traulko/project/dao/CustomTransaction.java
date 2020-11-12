package com.traulko.project.dao;

import com.traulko.project.dao.connection.ConnectionPool;
import com.traulko.project.dao.impl.*;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.ConnectionDatabaseException;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.TransactionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomTransaction {
    private static final Logger LOGGER = LogManager.getLogger();

    public boolean addOrderAndOrderItem(CustomOrder order, List<UserBasketProduct> userBasketProductList) throws TransactionException {
        Connection connection = null;
        UserBasketProductDao userBasketProductDao = new UserBasketProductDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean result = orderDao.add(order, connection);
            if (result) {
                for (UserBasketProduct userBasketProduct : userBasketProductList) {
                    if (!(orderItemDao.add(order, userBasketProduct.getProduct(), connection)
                            && userBasketProductDao.remove(userBasketProduct))) {
                        result = false;
                    }
                }
            }
            connection.commit();
            return result;
        } catch (ConnectionDatabaseException | SQLException | DaoException e) {
            rollback(connection);
            throw new TransactionException("Error while adding order and orderItem " + order, e);
        } finally {
            close(connection);
        }
    }

    public boolean addProductAndImage(Product product) throws TransactionException {
        Connection connection = null;
        ImageDao imageDao = new ImageDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean result = imageDao.add(product.getImage(), connection);
            if (result) {
                result = productDao.add(product, connection);
            }
            connection.commit();
            return result;
        } catch (ConnectionDatabaseException | SQLException | DaoException e) {
            rollback(connection);
            throw new TransactionException("Error while adding product and image " + product, e);
        } finally {
            close(connection);
        }
    }

    private void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Rollback error");
        }
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Close connection error");
            }
        }
    }
}
