package com.traulko.project.dao;

import com.traulko.project.entity.CustomImage;
import com.traulko.project.exception.DaoException;

import java.sql.Connection;

public interface ImageDao {
    boolean add(CustomImage image, Connection connection) throws DaoException;
}
