package com.bite.web.blog.servlet;


import com.bite.web.blog.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");

        User user;
        user = User.register(username, password,nickname);

        if (user == null) {
            resp.sendRedirect("blog_register.html");
            return;
        }

        // 注册完成后视为登录
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("注册成功，一秒后自动跳转！");
        resp.setHeader("refresh","1;http://localhost:8080/list.html");


        // 注册完成后还需要人工进行登录
        /*
        resp.sendRedirect("login.html");
        */
    }
}
