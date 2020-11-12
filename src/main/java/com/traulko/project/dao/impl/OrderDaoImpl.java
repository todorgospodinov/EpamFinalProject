package com.traulko.project.dao.impl;

import com.traulko.project.dao.ColumnName;
import com.traulko.project.dao.OrderDao;
import com.traulko.project.dao.connection.ConnectionPool;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.Product;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ConnectionDatabaseException;
import com.traulko.project.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final String ADD_ORDER = "INSERT INTO orders (order_creation_date, order_closing_date," +
            "order_status, user_id_fk) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_USER_ID = "SELECT order_id, order_creation_date, order_closing_date," +
            "order_status, user_id FROM orders INNER JOIN users ON user_id = user_id_fk where user_id = ?";
    private static final String FIND_BY_ID = "SELECT order_id, order_creation_date, order_closing_date," +
            "order_status, user_id FROM orders INNER JOIN users ON user_id = user_id_fk where order_id = ?";

    @Override
    public boolean add(CustomOrder order, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            Date creationDate = Date.valueOf(order.getCreationDate());
            statement.setLong(1, creationDate.getTime());
            Date closingDate = Date.valueOf(order.getCreationDate());
            statement.setLong(2, closingDate.getTime());
            statement.setString(3, order.getStatus().toString());
            statement.setInt(4, order.getUser().getUserId());
            boolean result = statement.executeUpdate() > 0;
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setOrderId(resultSet.getInt(1));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding order", e);
        }
    }

    @Override
    public List<CustomOrder> getOrdersByUserId(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<CustomOrder> orderList = new ArrayList<>();
            while (resultSet.next()) {
                CustomOrder order = createOrderFromResultSet(resultSet);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while finding baskets", e);
        }
    }

    @Override
    public Optional<CustomOrder> findById(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<CustomOrder> orderOptional = Optional.empty();
            if (resultSet.next()) {
                CustomOrder order = createOrderFromResultSet(resultSet);
                orderOptional = Optional.of(order);
            }
            return orderOptional;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding order by id error", e);
        }
    }

    private CustomOrder createOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = Integer.parseInt(resultSet.getString(ColumnName.ORDER_ID));
        long creationDateLong = resultSet.getLong(ColumnName.ORDER_CREATION_DATE);
        Date creationDate = new Date(creationDateLong);
        long closingDateLong = resultSet.getLong(ColumnName.ORDER_CLOSING_DATE);
        Date closingDate = new Date(closingDateLong);
        CustomOrder.Status orderStatus = CustomOrder.Status.valueOf(
                resultSet.getString(ColumnName.ORDER_STATUS));
        Integer userId = Integer.parseInt(resultSet.getString(ColumnName.USER_ID));
        User user = new User();
        user.setUserId(userId);
        return new CustomOrder(id, creationDate.toLocalDate(), closingDate.toLocalDate(),
                orderStatus, user);
    }
}
