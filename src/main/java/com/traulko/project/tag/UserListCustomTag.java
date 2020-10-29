package com.traulko.project.tag;

import com.traulko.project.controller.RequestParameter;
import com.traulko.project.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class UserListCustomTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(UserListCustomTag.class);

    @Override
    public int doStartTag() throws JspException {
        ServletRequest servletRequest = pageContext.getRequest();
        List<User> userList = (List<User>) servletRequest.getAttribute(RequestParameter.USERS);
        int index = 0;
        while (index < userList.size()) {
            try {
                pageContext.getOut().write("<form style=\"display: flex\" method=\"post\" action=\"controller\">\n"
                        + "<div class=\"btn\">" + userList.get(index) + "</div>" +
                        "<button class=\"btn btn-secondary nav-link\" name=\"commandName\"\n" +
                        "value=\"main_page\"></button>\n" +
                        "</form>");
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Error while write out");
            }
            index++;
        }
        return SKIP_BODY;
    }
}
