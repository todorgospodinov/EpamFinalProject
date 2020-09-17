package com.traulko.project.dao.impl;

import com.traulko.project.dao.UserDao;
import com.traulko.project.entity.User;
import com.traulko.project.exception.DaoException;

public class Main {
    public static void main(String[] args) throws DaoException {
        UserDao userDao = new UserDaoImpl();
        User user = new User("4", "1", true);
        System.out.println(userDao.findByLogin("traulko"));
        userDao.addUser(user);
    }
}
