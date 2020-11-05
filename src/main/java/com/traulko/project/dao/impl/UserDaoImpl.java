package com.traulko.project.dao.impl;

import com.traulko.project.builder.UserBuilder;
import com.traulko.project.dao.connection.ConnectionPool;
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
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT user_id, user_email, " +
            "user_name, user_surname, user_patronymic, user_role, user_status FROM users" +
            " WHERE user_email = ? AND user_password = ?";
    private static final String FIND_USER_BY_EMAIL = "SELECT user_id, user_email, " +
            "user_password, user_name, user_surname, user_patronymic, user_role, user_status FROM users" +
            " WHERE user_email = ?";
    private static final String ADD_USER = "INSERT INTO users (user_email, user_password, user_name," +
            " user_surname, user_patronymic, user_role, user_status) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users set user_email = ?, user_name = ?, user_surname = ?," +
            " user_patronymic = ?, user_role = ?, user_status = ? where user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT user_id, user_email, user_name, user_surname, user_patronymic," +
            " user_role, user_status FROM users";
    private static final String FIND_USERS_BY_SEARCH_QUERY = "SELECT user_id, user_email, user_name, user_surname, " +
            "user_patronymic, user_role, user_status FROM users where concat(user_id, user_email, user_name, user_surname," +
            " user_patronymic, user_role, user_status) like ?";
    private static final String DELETE_USER_BY_EMAIL = "DELETE FROM users where user_email = ?";
    private static final String BLOCK_USER = "UPDATE users SET user_status = \"BLOCKED\" where user_email = ?";
    private static final String UNBLOCK_USER = "UPDATE users SET user_status = \"ENABLE\" where user_email = ?";
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
            statement.setInt(7, Integer.valueOf(user.getUserId()));
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
    public boolean remove(String email) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_USER_BY_EMAIL);
            statement.setString(1, email);
            result = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Removing user by email error", e);
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }

    @Override
    public boolean block(String email) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(BLOCK_USER);
            statement.setString(1, email);
            result = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Blocking user error", e);
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }

    @Override
    public boolean unblock(String email) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UNBLOCK_USER);
            statement.setString(1, email);
            result = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoException("Unblocking user error", e);
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
    public boolean add(User user, String encryptedPassword) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, user.getEmail());
            statement.setString(2, encryptedPassword);
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getPatronymic());
            statement.setString(6, user.getRole().toString());
            statement.setString(7, user.getStatus().toString());
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
        Integer id = Integer.parseInt(resultSet.getString(ColumnName.USER_ID));
        String email = resultSet.getString(ColumnName.USER_EMAIL);
        User.Role role = User.Role.valueOf(resultSet.getString(ColumnName.USER_ROLE));
        User.Status status = User.Status.valueOf(resultSet.getString(ColumnName.USER_STATUS));
        String name = resultSet.getString(ColumnName.USER_NAME);
        String surname = resultSet.getString(ColumnName.USER_SURNAME);
        String patronymic = resultSet.getString(ColumnName.USER_PATRONYMIC);
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setUserId(id);
        userBuilder.setEmail(email);
        userBuilder.setRole(role);
        userBuilder.setStatus(status);
        userBuilder.setName(name);
        userBuilder.setSurname(surname);
        userBuilder.setPatronymic(patronymic);
        return userBuilder.getUser();
    }
}
