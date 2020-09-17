package com.traulko.project.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface CustomCommand {
    String execute(HttpServletRequest request);
}
