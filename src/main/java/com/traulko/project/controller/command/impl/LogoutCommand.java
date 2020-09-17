package com.traulko.project.controller.command.impl;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.LOGIN;
    }
}
