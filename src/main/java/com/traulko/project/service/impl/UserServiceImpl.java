package com.traulko.project.service.impl;

import com.traulko.project.dao.UserDao;
import com.traulko.project.dao.impl.UserDaoImpl;
import com.traulko.project.entity.User;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserService;
import com.traulko.project.util.CustomCipher;
import com.traulko.project.validator.UserValidator;
import com.traulko.project.validator.impl.UserValidatorImpl;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean isEmailFree(String email) throws ServiceException {
        UserValidator userValidator = new UserValidatorImpl();
        boolean result = false;
        if (userValidator.isEmailValid(email)) {
            try {
                Optional<User> optionalUser = userDao.findByEmail(email);
                result = optionalUser.isPresent() ? true : false;
            } catch (DaoException e) {
                throw new ServiceException("Error while checking free email " +
                        "for presence in database", e);
            }
        }
        return result;
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        User user;
        try {
            Optional<User> optionalUser = userDao.findByEmail(email);
            user = optionalUser.get();
        } catch (DaoException e) {
            throw new ServiceException("Error while getting user by email", e);
        }
        return user;
    }

    @Override
    public boolean isPasswordValid(String password) {
        UserValidator userValidator = new UserValidatorImpl();
        return userValidator.isPasswordValid(password);
    }

    @Override
    public boolean activateUser(String email) throws ServiceException {
        boolean result = false;
        try {
            Optional<User> optionalUser = userDao.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setStatus(User.Status.ENABLE);
                if (userDao.update(user)) {
                    result = true;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while updating user", e);
        }
        return result;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            List<User> userList = userDao.findAll();
            return userList;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all users in batabase", e);
        }
    }

    @Override
    public List<User> findBySearchQuery(String searchQuery) throws ServiceException {
        try {
            List<User> userList = userDao.findBySearchQuery(searchQuery);
            return userList;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding users by search query in batabase", e);
        }
    }

    @Override
    public boolean isEmailValid(String email) {
        UserValidator userValidator = new UserValidatorImpl();
        return userValidator.isEmailValid(email);
    }


    @Override
    public boolean isUserExists(String email, String password) throws ServiceException {
        UserValidator userValidator = new UserValidatorImpl();
        boolean result = false;
        if (userValidator.isEmailValid(email) && userValidator.isPasswordValid(password)) {
            try {
                CustomCipher cipher = new CustomCipher();
                String encryptedPassword = cipher.encrypt(password);
                Optional<User> optionalUser = userDao.findByEmailAndPassword(email, encryptedPassword);
                result = optionalUser.isPresent() ? true : false;
            } catch (DaoException | NoSuchAlgorithmException e) {
                throw new ServiceException("Error while checking user " +
                        "for presence in database", e);
            }
        }
        return result;
    }

    @Override
    public void registerUser(String email, String password,
                             String name, String surname, String patronymic) throws ServiceException {
        try {
            CustomCipher cipher = new CustomCipher();
            String encryptedPassword = cipher.encrypt(password);
            userDao.add(email, encryptedPassword, name, surname, patronymic);
        } catch (DaoException | NoSuchAlgorithmException e) {
            throw new ServiceException("Error while adding user", e);
        }
    }
}
