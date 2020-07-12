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
 * @Date 2020/7/12 15:58
 */
@WebServlet("/publish_success")
public class Publish_successServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        User.publish(user.id,title,content);
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("发表成功！");
        resp.setHeader("refresh","1;http://localhost:8080/list.html");
    }
}
