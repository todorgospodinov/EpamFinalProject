package com.traulko.project.controller.command.impl.page;

import com.traulko.project.controller.PagePath;
import com.traulko.project.controller.command.CustomCommand;

import javax.servlet.http.HttpServletRequest;

public class FillUpBalancePageCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.FILL_UP_BALANCE;
    }
}
