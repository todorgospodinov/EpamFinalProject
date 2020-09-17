package com.traulko.project.dao;

import com.traulko.project.entity.User;
import com.traulko.project.exception.DaoException;

import java.util.Optional;

public interface UserDao extends MainDao {
    Optional<User> findByLogin(String login) throws DaoException;
    boolean addUser(User user) throws DaoException;
}
