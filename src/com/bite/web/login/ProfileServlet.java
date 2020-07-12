package com.bite.web.login;

/**
 * @Author tjy
 * @Date 2020/7/10 19:18
 */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过请求 Cookie 中的 session-id，去 Session 中读取 Userr 对象
        // 如果读到了 Userr 对象，就代表用户已经登录了
        // 否则，代表用户没有登录过

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        HttpSession session = req.getSession();
        Userr userr = (Userr) session.getAttribute("user");   // 这个 "userr" key 必须和 setAttribute 时的 key 一致
        if (userr == null) {
            writer.println("<p>您没有登录，所以没有个人中心</p>");
        } else {
            writer.println("<p>欢迎您，" + userr.username + "</p>");
        }
    }
}

