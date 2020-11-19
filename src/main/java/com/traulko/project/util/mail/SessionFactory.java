package com.traulko.project.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * The {@code SessionFactory} class represents session factory.
 *
 * @author Yan Traulko
 * @version 1.0
 */
public class SessionFactory {

    /**
     * Create session.
     *
     * @param configProperties the config properties
     * @return the Session
     */
    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty("mail.user.name");
        String userPassword = configProperties.getProperty("mail.user.password");

        return Session.getDefaultInstance(configProperties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}
