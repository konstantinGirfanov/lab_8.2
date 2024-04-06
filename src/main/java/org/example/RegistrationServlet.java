package org.example;

import org.example.accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String email = req.getParameter("email");

        if (email.isEmpty() || login.isEmpty() || pass.isEmpty()) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Отсутсвует email, логин или пароль");
            return;
        }

        UserProfile profile = new UserProfile(login, pass, email);
        if(AccountService.getUserByLogin(login) == null){
            AccountService.AddNewUser(new UserProfile(login, pass, email));

            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("pass", pass);

            String url = req.getContextPath();
            resp.sendRedirect(url + "/login");
        }
    }
}
