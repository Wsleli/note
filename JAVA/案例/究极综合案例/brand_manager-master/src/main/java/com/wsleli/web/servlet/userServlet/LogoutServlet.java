package com.wsleli.web.servlet.userServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 清除cookie
        Cookie authCookie = new Cookie("authToken", "");
        authCookie.setMaxAge(0);
        resp.addCookie(authCookie);

        // 注销当前用户的会话信息
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 重定向到登录页面
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}