package com.traulko.project.dao;

import com.traulko.project.entity.Product;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ProductDao} interface represents product dao.
 *
 * @author Yan Traulko
 * @version 1.0
 */
public interface ProductDao {

    /**
     * Find all products.
     *
     * @return the list of products
     * @throws DaoException the dao exception
     */
    List<Product> findAll() throws DaoException;

    /**
     * Find product by id.
     *
     * @param productId the product index
     * @return the optional of product
     * @throws DaoException the dao exception
     */
    Optional<Product> findById(Integer productId) throws DaoException;

    /**
     * Find products by search query.
     *
     * @param searchQuery the search query
     * @return the list of products
     * @throws DaoException the dao exception
     */
    List<Product> findBySearchQuery(String searchQuery) throws DaoException;

    /**
     * Update product.
     *
     * @param product the product
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Product product) throws DaoException;

    /**
     * Update product.
     *
     * @param product the product
     * @param connection the connection
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Product product, Connection connection) throws DaoException;
}
