package com.wsleli.web.servlet.userServlet;

import com.wsleli.pojo.User;
import com.wsleli.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private final UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (service.UPIsEmpty(user)) {
            req.setAttribute("login_msg", "用户名或密码为空");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        String remember = req.getParameter("remember");

        User u = service.login(user);

        if (u != null) {
            //登陆成功
            if ("1".equals(remember)) {
                username = URLEncoder.encode(username, "utf-8");

                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);

                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);

                resp.addCookie(c_username);
                resp.addCookie(c_password);
            }

            HttpSession session = req.getSession();
            session.setAttribute("user", u);

            String contextPath = req.getContextPath();
            if ("root".equals(u.getUsername()) && "root".equals(u.getPassword())) {
                resp.sendRedirect(contextPath + "/rootBrand.html");
            } else {
                resp.sendRedirect(contextPath + "/userBrand.html");
            }
        } else {
            //登陆失败
            req.setAttribute("login_msg", "用户名或密码错误");

            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
