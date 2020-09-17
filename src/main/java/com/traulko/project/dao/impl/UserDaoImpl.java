package com.traulko.project.dao.impl;

import com.traulko.project.connection.ConnectionPool;
import com.traulko.project.dao.ColumnName;
import com.traulko.project.dao.UserDao;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ConnectionDatabaseException;
import com.traulko.project.exception.DaoException;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String FIND_USER_BY_LOGIN = "SELECT login, " +
            "password, isEnable FROM users WHERE login = ?";
    private static final String ADD_USER = "INSERT INTO users (login, password, isEnable) " +
            "values (?, ?, ?)";

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding user by login error", e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean addUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isEnable());
            result = statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Adding user error", e);
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString(ColumnName.LOGIN);
        String password = resultSet.getString(ColumnName.PASSWORD);
        boolean isEnable = resultSet.getBoolean(ColumnName.IS_ENABLE);
        User user = new User(login, password, isEnable);
        return user;
    }
}
