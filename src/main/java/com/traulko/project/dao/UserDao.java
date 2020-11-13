package com.traulko.project.dao;

import com.traulko.project.entity.User;
import com.traulko.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByEmailAndPassword(String login, String password) throws DaoException;

    Optional<User> findByEmail(String login) throws DaoException;

    Optional<User> findById(Integer id) throws DaoException;

    boolean update(User user) throws DaoException;

    boolean remove(String email) throws DaoException;

    boolean block(String email) throws DaoException;

    boolean unblock(String email) throws DaoException;

    List<User> findAll() throws DaoException;

    List<User> findBySearchQuery(String searchQuery) throws DaoException;

    boolean changePassword(String email, String password) throws DaoException;

    boolean add(User user, String encryptedPassword) throws DaoException;
}
