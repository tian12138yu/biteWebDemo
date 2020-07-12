package com.bite.web.Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author tjy
 * @Date 2020/7/9 19:01
 */
@WebServlet("/demo1")
public class Demo1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendError(404,"你是猪");
//        int i = 10 / 0;
//        resp.setStatus(303);
        resp.setStatus(307);
        resp.setHeader("Location","aaa");

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(307);
        resp.setHeader("Location","aaa");
    }
}
