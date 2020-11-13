package com.traulko.project.dao.impl;

import com.traulko.project.dao.ColumnName;
import com.traulko.project.dao.OrderItemDao;
import com.traulko.project.dao.connection.ConnectionPool;
import com.traulko.project.entity.*;
import com.traulko.project.exception.ConnectionDatabaseException;
import com.traulko.project.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {
    private static final OrderItemDaoImpl INSTANCE = new OrderItemDaoImpl();
    private static final String ADD_ORDER_ITEM = "INSERT INTO order_item (product_id_fk, order_id_fk)" +
            " VALUES (?, ?)";
    private static final String FIND_BY_ORDER_ID = "SELECT order_item_id, product_id, product_title," +
            "product_price, product_description, image_id, image_name, order_id from order_item INNER JOIN products " +
            "ON product_id_fk = product_id INNER JOIN images ON image_id_fk = image_id INNER JOIN orders ON " +
            "order_id_fk = order_id where order_id = ?";

    private OrderItemDaoImpl() {
    }

    public static OrderItemDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(CustomOrder order, Product product, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_ORDER_ITEM)) {
            statement.setInt(1, product.getProductId());
            statement.setInt(2, order.getOrderId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding orderItem: " + product, e);
        }
    }

    @Override
    public List<OrderItem> findOrderItemsByOrderId(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ORDER_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<OrderItem> orderItemList = new ArrayList<>();
            while (resultSet.next()) {
                OrderItem orderItem = createOrderItemFromResultSet(resultSet);
                orderItemList.add(orderItem);
            }
            return orderItemList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while finding baskets", e);
        }
    }

    private OrderItem createOrderItemFromResultSet(ResultSet resultSet) throws SQLException {
        Integer orderItemId = Integer.parseInt(resultSet.getString(ColumnName.ORDER_ITEM_ID));
        Integer productId = Integer.parseInt(resultSet.getString(ColumnName.PRODUCT_ID));
        String productTitle = resultSet.getString(ColumnName.PRODUCT_TITLE);
        double productPrice = resultSet.getLong(ColumnName.PRODUCT_PRICE);
        String productDescription = resultSet.getString(ColumnName.PRODUCT_DESCRIPTION);
        Integer imageId = Integer.parseInt(resultSet.getString(ColumnName.IMAGE_ID));
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        Integer orderId = Integer.parseInt(resultSet.getString(ColumnName.ORDER_ID));
        CustomOrder order = new CustomOrder();
        order.setOrderId(orderId);
        CustomImage image = new CustomImage(imageId, imageName);
        Product product = new Product(productId, productTitle, productPrice, productDescription, image);
        return new OrderItem(orderItemId, product, order);
    }
}
