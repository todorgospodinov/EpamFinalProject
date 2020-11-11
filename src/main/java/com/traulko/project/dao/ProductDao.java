package com.traulko.project.dao;

import com.traulko.project.entity.Product;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> findAll() throws DaoException;

    Optional<Product> findById(int id) throws DaoException;

    List<Product> findBySearchQuery(String searchQuery) throws DaoException;

    boolean add(Product product, Connection connection) throws DaoException;
}
