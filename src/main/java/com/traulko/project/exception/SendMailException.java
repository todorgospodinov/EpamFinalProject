package com.traulko.project.exception;

/**
 * The {@code SendMailException} class represents send mail exception.
 *
 * @author Yan Traulko
 * @version 1.0
 */
public class SendMailException extends Exception {

    /**
     * Instantiates a new SendMailException.
     *
     * @param throwable the throwable
     */
    public SendMailException(Throwable throwable) {
        super(throwable);
    }
}
