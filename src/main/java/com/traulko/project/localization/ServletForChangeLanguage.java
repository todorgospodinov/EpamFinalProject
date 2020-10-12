package com.traulko.project.localization;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/servletForChangeLanguage")
public class ServletForChangeLanguage extends HttpServlet {
    public ServletForChangeLanguage() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String previousRequest = request.getParameter("previousRequest");
        request.getSession(true).setAttribute("locale", request.getParameter("locale"));
        response.sendRedirect(previousRequest);
    }
}
