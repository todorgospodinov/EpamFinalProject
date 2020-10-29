package com.traulko.project.dao.impl;

import com.traulko.project.connection.ConnectionPool;
import com.traulko.project.dao.ColumnName;
import com.traulko.project.dao.UserDao;
import com.traulko.project.entity.User;
import com.traulko.project.exception.ConnectionDatabaseException;
import com.traulko.project.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT id, email, " +
            "name, surname, patronymic, role, status FROM users WHERE email = ? AND password = ?";
    private static final String FIND_USER_BY_EMAIL = "SELECT id, email, " +
            "password, name, surname, patronymic, role, status FROM users WHERE email = ?";
    private static final String ADD_USER = "INSERT INTO users (email, password, name," +
            " surname, patronymic, role, status) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users set email = ?, name = ?, surname = ?," +
            " patronymic = ?, role = ?, status = ? where id = ?";
    private static final String FIND_ALL_USERS = "SELECT id, email, name, surname, patronymic," +
            " role, status FROM users";
    private static final String FIND_USERS_BY_SEARCH_QUERY = "SELECT id, email, name, surname, " +
            "patronymic, role, status FROM users where concat(id, email, name, surname," +
            " patronymic, role, status) like ?";
    private static final String PERCENT = "%";

    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding user by email and password error", e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public boolean update(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getPatronymic());
            statement.setString(5, String.valueOf(user.getRole()));
            statement.setString(6, String.valueOf(user.getStatus()));
            statement.setInt(7, Integer.valueOf(user.getId()));
            result = statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Updating user error", e);
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }

    @Override
    public List<User> findAll() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                userList.add(user);
            }
            return userList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding all users error", e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<User> findBySearchQuery(String searchQuery) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_USERS_BY_SEARCH_QUERY);
            statement.setString(1, PERCENT + searchQuery + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                userList.add(user);
            }
            return userList;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding users by search query error", e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public Optional<User> findByEmail(String email) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Finding user by email error", e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean add(String email, String password, String name,
                       String surname, String patronymic) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, surname);
            statement.setString(5, patronymic);
            statement.setString(6, User.Role.USER.toString());
            statement.setString(7, User.Status.NOT_CONFIRMED.toString());
            result = statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Adding user to users table error", e);
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = Integer.parseInt(resultSet.getString(ColumnName.ID));
        String email = resultSet.getString(ColumnName.EMAIL);
        User.Role role = User.Role.valueOf(resultSet.getString(ColumnName.ROLE));
        User.Status status = User.Status.valueOf(resultSet.getString(ColumnName.STATUS));
        String name = resultSet.getString(ColumnName.NAME);
        String surname = resultSet.getString(ColumnName.SURNAME);
        String patronymic = resultSet.getString(ColumnName.PATRONYMIC);
        User user = new User(id, email, name, surname, patronymic, role, status);
        return user;
    }
}
