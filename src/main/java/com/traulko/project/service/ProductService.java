package com.traulko.project.service;

import com.traulko.project.entity.Product;
import com.traulko.project.exception.ServiceException;

import java.util.List;

public interface ProductService {
    boolean add(String productTitle, String price, String description, String imageName) throws ServiceException;

    List<Product> findAll() throws ServiceException;
}
