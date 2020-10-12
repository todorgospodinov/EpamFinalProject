package com.traulko.project.controller.command.impl.transition;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;

public class GoToMainPageCommand implements CustomCommand {

    @Override
    public String execute(HttpServletRequest request) {
        // TODO: 11.10.2020 loadItems 
        return PagePath.MAIN;
    }
}
