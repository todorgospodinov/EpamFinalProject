package com.traulko.project.dao.impl;

import com.traulko.project.builder.ProductBuilder;
import com.traulko.project.dao.ColumnName;
import com.traulko.project.dao.ProductDao;
import com.traulko.project.dao.connection.ConnectionPool;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.ConnectionDatabaseException;
import com.traulko.project.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final String FIND_ALL_PRODUCTS = "SELECT product_id, product_title, product_price," +
            " product_description, product_image FROM products";
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

    private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = Integer.parseInt(resultSet.getString(ColumnName.PRODUCT_ID));
        String title = resultSet.getString(ColumnName.PRODUCT_TITLE);
        double price = resultSet.getDouble(ColumnName.PRODUCT_PRICE);
        String description = resultSet.getString(ColumnName.PRODUCT_DESCRIPTION);
        Blob image = resultSet.getBlob(ColumnName.PRODUCT_IMAGE);
        ProductBuilder productBuilder = new ProductBuilder();
        productBuilder.setProductId(id);
        productBuilder.setTitle(title);
        productBuilder.setPrice(price);
        productBuilder.setDescription(description);
        productBuilder.setImage(image);
        return productBuilder.getProduct();
    }
}
