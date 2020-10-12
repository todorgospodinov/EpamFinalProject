package com.traulko.project.exception;

public class SendMailException extends Exception {
    public SendMailException() {
        super();
    }

    public SendMailException(String message) {
        super(message);
    }

    public SendMailException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public SendMailException(Throwable throwable) {
        super(throwable);
    }
}
