package com.traulko.project.dao;

import com.traulko.project.entity.User;
import com.traulko.project.exception.DaoException;

import java.util.Optional;

public interface UserDao extends MainDao {
    Optional<User> findByEmailAndPassword(String login, String password) throws DaoException;
    Optional<User> findByEmail(String login) throws DaoException;
    boolean addUser(String login, String password, String name, String surname, String patronymic) throws DaoException;
    boolean updateUser(User user) throws DaoException;
}
