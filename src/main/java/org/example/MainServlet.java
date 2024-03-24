package org.example;

import org.example.accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/files")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = (String) req.getSession().getAttribute("login");
        String pass = (String) req.getSession().getAttribute("pass");

        if(AccountService.getUserByLogin(login) == null || !AccountService.getUserByLogin(login).getPass().equals(pass)) {
            String url = req.getContextPath();
            resp.sendRedirect(url + "/login");
            return;
        }

        String path = req.getParameter("path");
        if(path.length() < ("C:\\test\\".length() + login.length())){
            path = "C:\\test\\" + login;
        }

        File directory = new File(path);
        File[] files = directory.listFiles();

        req.setAttribute("files", files);
        req.setAttribute("path", path);
        req.setAttribute("login", login);

        req.getRequestDispatcher("files.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("login");
        req.getSession().removeAttribute("pass");

        String url = req.getContextPath();
        resp.sendRedirect(url + "/login");
    }
}