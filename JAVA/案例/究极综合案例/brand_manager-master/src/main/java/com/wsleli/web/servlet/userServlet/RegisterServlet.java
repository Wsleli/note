package com.wsleli.web.servlet.userServlet;

import com.wsleli.pojo.User;
import com.wsleli.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
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
            req.setAttribute("register_msg", "用户名或密码为空");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        String checkCode = req.getParameter("checkCode");
        HttpSession session = req.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            req.setAttribute("register_msg", "验证码错误");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        boolean flag = service.register(user);
        if (flag) {
            req.setAttribute("register_msg", "注册成功，请登录");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("register_msg", "用户名已存在");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
