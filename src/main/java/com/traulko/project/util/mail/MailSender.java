package com.traulko.project.util.mail;

import com.traulko.project.exception.SendMailException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {
    private static final Logger LOGGER = LogManager.getLogger(MailSender.class);
    private static final String PROPERTY_NAME = "property/mail.properties";
    private static final String MAIL_SUBJECT = "Online store";
    private static final String MAIL_MESSAGE = "Register in online store\nTo make your account enable, follow this link\n";
    private static final String MAIL_MESSAGE_LINK = "%s?commandName=activate_account&email=";

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public MailSender(String email, String url) {
        this.sendToEmail = email;
        this.mailSubject = MAIL_SUBJECT;
        this.mailText = MAIL_MESSAGE + String.format(MAIL_MESSAGE_LINK, url) + email;
        properties = getProperties();
    }

    public void send() throws SendMailException {
        try {
            initMessage();
            Transport.send(message);
        } catch (MessagingException e) {
            throw new SendMailException(e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        ClassLoader classLoader = MailSender.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(PROPERTY_NAME);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error while reading properties");
        }
        return properties;
    }
}
