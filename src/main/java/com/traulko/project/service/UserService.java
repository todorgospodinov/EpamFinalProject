package com.traulko.project.service;

import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean isUserExists(String email, String password) throws ServiceException;

    boolean fillUpBalance(String userId, String moneyAmount) throws ServiceException;

    void sendLetter(User user, String url) throws ServiceException;

    boolean activateUser(String email) throws ServiceException;

    List<User> findAll() throws ServiceException;

    List<User> findBySearchQuery(String searchQuery) throws ServiceException;

    boolean changePassword(String email, String password, String passwordRepeat) throws ServiceException;

    Optional<User> findById(String id) throws ServiceException;

    Optional<User> findUserByEmail(String email) throws ServiceException;

    Optional<User> findUserByAccessCode(String code, List<User> userList) throws ServiceException;

    boolean remove(String email) throws ServiceException;

    boolean block(String email) throws ServiceException;

    boolean unblock(String email) throws ServiceException;

    boolean add(Map<String, String> parameters) throws ServiceException;
}
