package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code LoginPageCommand} class represents browse login page command.
 *
 * @author Yan Traulko
 * @version 1.0
 */
public class LoginPageCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.LOGIN;
    }
}
