package com.traulko.project.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public interface MainDao {
    default void close(Statement statement) {
        final Logger logger = LogManager.getLogger(MainDao.class);
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Statement hasn't been closed");
            }
        }
    }

    default void close(Connection connection) {
        final Logger logger = LogManager.getLogger(MainDao.class);
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Statement hasn't been closed");
            }
        }
    }

    default void close(ResultSet resultSet) {
        final Logger logger = LogManager.getLogger(MainDao.class);
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Statement hasn't been closed");
            }
        }
    }
}
