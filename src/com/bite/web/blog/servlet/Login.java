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

/**
 * @Author tjy
 * @Date 2020/7/12 14:48
 */
@WebServlet("/login1")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = User.login(username, password);
        if (login == null){
            writer.println("账号或密码错误，请重新输入！");
            resp.setHeader("refresh","1;http://localhost:8080/blog_login.html");
        }else {
            writer.println("登陆成功,一秒后自动跳转！");
            HttpSession session = req.getSession();
            session.setAttribute("user", login);
            resp.setHeader("refresh","1;http://localhost:8080/list.html");
        }

    }
}
