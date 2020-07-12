package com.bite.web.login;

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
 * @Date 2020/7/10 19:32
 */
@WebServlet("/Rsucccess")
public class RegisterSuccess extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html; charset=utf-8");
        HttpSession session = req.getSession();
        Userr userr = new Userr(username, password);
        session.setAttribute("user", userr);
        Userr.insert(username,password);
        PrintWriter writer = resp.getWriter();
        writer.println("注册成功");
        writer.println("三秒后自动跳转！");
        resp.setHeader("refresh","3;http://localhost:8080/profile");

    }
}
