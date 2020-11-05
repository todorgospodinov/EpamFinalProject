package com.traulko.project.service;

import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;

import java.util.List;

public interface UserService {
    boolean isUserExists(String email, String password) throws ServiceException;

    void sendConfirmRegistrationLetter(String email, String url) throws ServiceException;

    boolean activateUser(String email) throws ServiceException;

    List<User> findAll() throws ServiceException;

    List<User> findBySearchQuery(String searchQuery) throws ServiceException;

    User findUserByEmail(String email) throws ServiceException;

    boolean remove(String email) throws ServiceException;

    boolean block(String email) throws ServiceException;

    boolean unblock(String email) throws ServiceException;

    boolean add(String email, String password, String passwordRepeat,
                String name, String surname, String patronymic) throws ServiceException;
}
