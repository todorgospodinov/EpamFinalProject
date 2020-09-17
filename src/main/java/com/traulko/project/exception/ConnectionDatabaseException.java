package com.traulko.project.exception;

public class ConnectionDatabaseException extends Exception {
    public ConnectionDatabaseException() {
        super();
    }

    public ConnectionDatabaseException(String message) {
        super(message);
    }

    public ConnectionDatabaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ConnectionDatabaseException(Throwable throwable) {
        super(throwable);
    }
}
