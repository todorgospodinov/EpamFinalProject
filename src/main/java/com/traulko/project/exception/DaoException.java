package com.traulko.project.exception;

public class DaoException extends Exception {
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
