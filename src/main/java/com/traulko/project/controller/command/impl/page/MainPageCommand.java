package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MainPageCommand implements CustomCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
//        if (session.getAttribute(RequestParameter.ROLE) == null) {
//            session.setAttribute(RequestParameter.ROLE, UserRole.GUEST.getName());
//        }
        return PagePath.MAIN;
    }
}
