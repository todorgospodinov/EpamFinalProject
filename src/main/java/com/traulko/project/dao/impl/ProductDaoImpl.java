package com.traulko.project.dao.impl;

import com.traulko.project.dao.ColumnName;
import com.traulko.project.dao.ProductDao;
import com.traulko.project.dao.connection.ConnectionPool;
import com.traulko.project.entity.Image;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.ConnectionDatabaseException;
import com.traulko.project.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final String FIND_ALL_PRODUCTS = "SELECT product_id, product_title, product_price," +
            " product_description, image_id, image_name FROM products INNER JOIN images ON" +
            " products.image_id_fk = images.image_id";
    private static final String ADD_PRODUCT = "INSERT INTO products (product_title, product_price, "
            + "product_description, image_id_fk) VALUES (?, ?, ?, ?)";

    @Override
    public List<Product> findAll() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_PRODUCTS);
            ResultSet resultSet = statement.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = createProductFromResultSet(resultSet);
                productList.add(product);
            }
            return productList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding all products error", e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean add(Product product, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setString(1, product.getTitle());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getImage().getImageId());
            boolean result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while adding tattoo: " + product, e);
        }
    }

    private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = Integer.parseInt(resultSet.getString(ColumnName.PRODUCT_ID));
        String title = resultSet.getString(ColumnName.PRODUCT_TITLE);
        double price = resultSet.getDouble(ColumnName.PRODUCT_PRICE);
        String description = resultSet.getString(ColumnName.PRODUCT_DESCRIPTION);
        Integer imageId = Integer.parseInt(resultSet.getString(ColumnName.IMAGE_ID));
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        return new Product(id, title, price, description, new Image(imageId, imageName));
    }
}