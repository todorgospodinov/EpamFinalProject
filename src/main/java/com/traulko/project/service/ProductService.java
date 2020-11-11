package com.traulko.project.service;

import com.traulko.project.entity.Product;
import com.traulko.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    boolean add(String productTitle, String price, String description, String imageName) throws ServiceException;

    Optional<Product> findById(String id) throws ServiceException;

    List<Product> findBySearchQuery(String searchQuery) throws ServiceException;

    List<Product> findAll() throws ServiceException;
}
