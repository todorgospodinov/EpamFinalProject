package com.traulko.project.service.impl;

import com.traulko.project.builder.UserBuilder;
import com.traulko.project.dao.UserDao;
import com.traulko.project.dao.impl.UserDaoImpl;
import com.traulko.project.entity.User;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.SendMailException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.UserService;
import com.traulko.project.util.CustomCipher;
import com.traulko.project.util.mail.MailSender;
import com.traulko.project.validator.UserValidator;
import com.traulko.project.validator.impl.UserValidatorImpl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User findUserByEmail(String email) throws ServiceException {
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
    public boolean remove(String email) throws ServiceException {
        boolean isRemoved = false;
        try {
            isRemoved = userDao.remove(email);
        } catch (DaoException e) {
            throw new ServiceException("Error while removing user by email", e);
        }
        return isRemoved;
    }

    @Override
    public boolean block(String email) throws ServiceException {
        boolean isBlocked = false;
        try {
            isBlocked = userDao.block(email);
        } catch (DaoException e) {
            throw new ServiceException("Error while blocking user", e);
        }
        return isBlocked;
    }

    @Override
    public boolean unblock(String email) throws ServiceException {
        boolean isUnblocked = false;
        try {
            isUnblocked = userDao.unblock(email);
        } catch (DaoException e) {
            throw new ServiceException("Error while unblocking user", e);
        }
        return isUnblocked;
    }

    @Override
    public void sendConfirmRegistrationLetter(String email, String url) throws ServiceException {
        try {
            MailSender sender = new MailSender(email, url);
            sender.send();
        } catch (SendMailException e) {
            throw new ServiceException("Error while sending message", e);
        }
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
    public boolean add(String email, String password, String passwordRepeat,
                       String name, String surname, String patronymic) throws ServiceException {
        UserValidator userValidator = new UserValidatorImpl();
        boolean result = false;
        if (userValidator.isEmailValid(email) && userValidator.isPasswordValid(password) &&
                userValidator.isNameValid(name) && userValidator.isNameValid(surname) &&
                userValidator.isNameValid(patronymic) && password.equals(passwordRepeat) &&
                isEmailFree(email)) {
            try {
                String encryptedPassword = CustomCipher.encrypt(password);
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setEmail(email);
                userBuilder.setName(name);
                userBuilder.setSurname(surname);
                userBuilder.setPatronymic(patronymic);
                userBuilder.setRole(User.Role.USER);
                userBuilder.setStatus(User.Status.NOT_CONFIRMED);
                User user = userBuilder.getUser();
                if(userDao.add(user, encryptedPassword)) {
                    result = true;
                }
            } catch (DaoException | NoSuchAlgorithmException e) {
                throw new ServiceException("Error while adding user", e);
            }
        }
        return result;
    }

    private boolean isEmailFree(String email) throws ServiceException {
        UserValidator userValidator = new UserValidatorImpl();
        boolean result = false;
        if (userValidator.isEmailValid(email)) {
            try {
                Optional<User> optionalUser = userDao.findByEmail(email);
                result = optionalUser.isPresent() ? false : true;
            } catch (DaoException e) {
                throw new ServiceException("Error while checking free email " +
                        "for presence in database", e);
            }
        }
        return result;
    }
}
