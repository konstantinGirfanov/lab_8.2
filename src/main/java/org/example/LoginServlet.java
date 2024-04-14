package org.example;

import org.example.accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    AccountService service = new AccountService();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        UserProfile profile = service.getUserByLogin(login);
        if (profile == null || !profile.getPassword().equals(pass)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Неправильный логин или пароль");
            return;
        }

        req.getSession().setAttribute("login",login);
        req.getSession().setAttribute("pass",pass);

        String url = req.getContextPath();
        resp.sendRedirect(url + "/files?path=C:/test/" + login);
    }
}
