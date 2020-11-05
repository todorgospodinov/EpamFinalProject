package com.traulko.project.service;

import com.traulko.project.entity.Product;
import com.traulko.project.exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws ServiceException;
}
