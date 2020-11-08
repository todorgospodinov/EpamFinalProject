package com.traulko.project.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(urlPatterns = "/image/*")
public class FileUploadController extends HttpServlet {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String DIRECTORY = "load.location";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getPathInfo().substring(1);
        String directory = getServletContext().getInitParameter(DIRECTORY);
        Path path = Paths.get(directory, filename);
        response.setHeader(CONTENT_TYPE, getServletContext().getMimeType(filename));
        response.setHeader(CONTENT_LENGTH, String.valueOf(Files.size(path)));
        Files.copy(path, response.getOutputStream());
    }
}