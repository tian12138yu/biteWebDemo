package com.bite.web.login;

/**
 * @Author tjy
 * @Date 2020/7/10 19:17
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

// 没有做错误处理
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        // 1. 读取用户的输入（username + password）
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 2. 验证用户输入的正确性，并得到 Userr 对象
        Userr userr = Userr.login(username, password);
        if (userr == null) {
            // 代表用户输入的用户名或者密码错误
            writer.println("<p>用户名或者密码错误</p>");
            return;
        }

        // 3. 设置 Cookie 和 Session，设置了 Session，背后 Tomcat 的代码会同时设置 Cookie 信息
        HttpSession session = req.getSession();
        session.setAttribute("user", userr);
        // 4. 显示登陆成功
        writer.println("<p>登陆成功</p>");
        writer.println("三秒后自动跳转！");
        resp.setHeader("refresh","3;http://localhost:8080/profile");

    }
}

