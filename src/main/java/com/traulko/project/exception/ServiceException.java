package com.traulko.project.exception;

/**
 * The {@code ServiceException} class represents service exception.
 *
 * @author Yan Traulko
 * @version 1.0
 */
public class ServiceException extends Exception{

    /**
     * Instantiates a new ServiceException.
     */
    public ServiceException() {
        super();
    }

    /**
     * Instantiates a new ServiceException.
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new ServiceException.
     *
     * @param message the message
     * @param throwable the throwable
     */
    public ServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new ServiceException.
     *
     * @param throwable the throwable
     */
    public ServiceException(Throwable throwable) {
        super(throwable);
    }
}
