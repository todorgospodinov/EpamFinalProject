package com.traulko.project.exception;

public class TransactionException extends Exception {
    public TransactionException() {
        super();
    }

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TransactionException(Throwable throwable) {
        super(throwable);
    }
}
