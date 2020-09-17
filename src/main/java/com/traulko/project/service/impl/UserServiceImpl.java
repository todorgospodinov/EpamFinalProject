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
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Override
    public boolean isUserExists(String login, String password) throws ServiceException {
        UserValidator userValidator = new UserValidatorImpl();
        UserDao userDao = new UserDaoImpl();
        boolean result = false;
        if (userValidator.isLoginValid(login) && userValidator.isPasswordValid(password)) {
            try {
                Optional<User> optionalUser = userDao.findByLogin(login);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    CustomCipher customCipher = new CustomCipher();
                    String encryptedPassword = customCipher.encrypt(password);
                    result = user.getLogin().equals(login) &&
                            user.getPassword().equals(encryptedPassword);
                }
            } catch (DaoException | NoSuchAlgorithmException e) {
                throw new ServiceException("Error while checking user " +
                        "for presence in database", e);
            }
        }
        return result;
    }

    @Override
    public void registerUser(String login, String password, boolean isEnable) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        User user = new User(login, password, isEnable);
        try {
            userDao.addUser(user);
        } catch (DaoException e) {
            throw new ServiceException("Error while adding user", e);
        }
    }
}
