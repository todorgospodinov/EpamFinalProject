package com.traulko.project.controller;

import com.traulko.project.controller.command.CommandProvider;
import com.traulko.project.controller.command.CustomCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/controller")
public class FrontController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        CustomCommand command = CommandProvider.defineCommand(commandName);
        String page = command.execute(request);
        HttpSession session = request.getSession();
        session.setAttribute(RequestParameter.CURRENT_PAGE, page);
        RequestAttributeHandler requestAttributeHandler = new RequestAttributeHandler();
        requestAttributeHandler.setAttributes(request);
        session.setAttribute(RequestParameter.REQUEST_ATTRIBUTE_HANDLER,
                requestAttributeHandler);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
