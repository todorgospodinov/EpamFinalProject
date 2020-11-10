package com.traulko.project.dao.impl;

import com.traulko.project.dao.BasketDao;
import com.traulko.project.dao.ColumnName;
import com.traulko.project.dao.connection.ConnectionPool;
import com.traulko.project.entity.Basket;
import com.traulko.project.entity.CustomImage;
import com.traulko.project.entity.Product;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ConnectionDatabaseException;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDaoImpl implements BasketDao {
    private static final String ADD_PRODUCT_TO_BASKET = "INSERT INTO basket (user_id_fk, product_id_fk) " +
            "VALUES (?, ?)";
    private static final String FIND_BY_USER_ID = "SELECT basket_id, user_id, product_id, product_title, product_price," +
            "product_description, image_id, image_name FROM basket INNER JOIN products ON product_id_fk = product_id " +
            "INNER JOIN images ON image_id = image_id_fk INNER JOIN users ON user_id_fk = user_id where user_id = ?";
    private static final String REMOVE_PRODUCT_FROM_BASKET = "DELETE FROM basket where user_id_fk = ? AND product_id_fk = ? LIMIT 1";

    @Override
    public boolean add(Basket basket) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_TO_BASKET)) {
            statement.setInt(1, basket.getUser().getUserId());
            statement.setInt(2, basket.getProduct().getProductId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while adding basket: " + basket, e);
        }
    }

    @Override
    public boolean remove(Basket basket) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT_FROM_BASKET)) {
            statement.setInt(1, basket.getUser().getUserId());
            statement.setInt(2, basket.getProduct().getProductId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while removing basket: " + basket, e);
        }
    }

    @Override
    public List<Basket> getBasketsByUserId(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Basket> basketList = new ArrayList<>();
            while (resultSet.next()) {
                Basket basket = createBasketFromResultSet(resultSet);
                basketList.add(basket);
            }
            return basketList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Error while finding baskets", e);
        }
    }

    private Basket createBasketFromResultSet(ResultSet resultSet) throws SQLException {
        Integer basketId = resultSet.getInt(ColumnName.BASKET_ID);
        Integer userId = resultSet.getInt(ColumnName.USER_ID);
        Integer productId = resultSet.getInt(ColumnName.PRODUCT_ID);
        double price = resultSet.getDouble(ColumnName.PRODUCT_PRICE);
        String title = resultSet.getString(ColumnName.PRODUCT_TITLE);
        String description = resultSet.getString(ColumnName.PRODUCT_DESCRIPTION);
        Integer imageId = Integer.parseInt(resultSet.getString(ColumnName.IMAGE_ID));
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        User user = new User();
        user.setUserId(userId);
        CustomImage image = new CustomImage(imageId, imageName);
        Product product = new Product(productId, title, price, description, image);
        return new Basket(basketId, user, product);
    }
}
