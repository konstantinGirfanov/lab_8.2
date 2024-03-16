package org.example;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;

@WebServlet("/files")
public class MainServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getParameter("path");

        File dir = new File(path);
        File[] files = dir.listFiles();

        req.setAttribute("files", files);

        req.getRequestDispatcher("files.jsp").forward(req, resp);
    }
}
