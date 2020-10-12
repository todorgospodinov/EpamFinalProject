package com.traulko.project.service;

import com.traulko.project.entity.User;
import com.traulko.project.exception.ServiceException;

public interface UserService {
    void registerUser(String email, String password,
                      String name, String surname, String patronymic) throws ServiceException;
    boolean isUserExists(String email, String password) throws ServiceException;
    boolean isEmailFree(String email) throws ServiceException;
    public boolean isEmailValid(String email);
    boolean isPasswordValid(String password);
    boolean activateUser(String email) throws ServiceException;
    public User getUserByEmail(String email) throws ServiceException;
}
