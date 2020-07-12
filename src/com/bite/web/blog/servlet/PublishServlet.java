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
 * @Date 2020/7/12 15:47
 */
@WebServlet("/publish")
public class PublishServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (user == null){
            writer.println("您当前并未登录，1秒后跳转到登录页面！");
            resp.setHeader("refresh","1;http://localhost:8080/list.html");
        }else {
            resp.sendRedirect("http://localhost:8080/publish.html");
        }
    }
}
