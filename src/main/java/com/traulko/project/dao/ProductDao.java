package com.traulko.project.dao;

import com.traulko.project.dao.MainDao;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface ProductDao extends MainDao {
    List<Product> findAll() throws DaoException;

    boolean add(Product product, Connection connection) throws DaoException;
}
