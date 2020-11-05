package com.traulko.project.service.impl;

import com.traulko.project.dao.ProductDao;
import com.traulko.project.dao.impl.ProductDaoImpl;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> findAll() throws ServiceException {
        try {
            List<Product> productList = productDao.findAll();
            return productList;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all products", e);
        }
    }
}
