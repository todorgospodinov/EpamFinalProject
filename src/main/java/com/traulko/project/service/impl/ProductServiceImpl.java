package com.traulko.project.service.impl;

import com.traulko.project.dao.CustomTransaction;
import com.traulko.project.dao.ProductDao;
import com.traulko.project.dao.impl.ProductDaoImpl;
import com.traulko.project.entity.Image;
import com.traulko.project.entity.Product;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.exception.TransactionException;
import com.traulko.project.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    private CustomTransaction customTransaction = new CustomTransaction();

    @Override
    public boolean add(String productTitle, String price, String description, String imageName) throws ServiceException {
        try {
            Product product = new Product(null, productTitle,
                    Double.parseDouble(price), description, new Image(null, imageName));
            return customTransaction.addProductAndImage(product);
        } catch (TransactionException e) {
            throw new ServiceException("Error while adding product", e);
        }
    }

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
