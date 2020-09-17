package com.traulko.project.service;

import com.traulko.project.exception.ServiceException;

public interface UserService {
    boolean isUserExists(String login, String password) throws ServiceException;
    void registerUser(String login, String password, boolean isEnable) throws ServiceException;
}
