package com.traulko.project.dao;

import com.traulko.project.entity.Image;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;

public interface ImageDao {
    boolean add(Image image, Connection connection) throws DaoException, DaoException;
}
