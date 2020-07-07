package com.bite.web;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author tjy
 * @Date 2020/7/5 19:39
 */

public class FirstDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String wd = req.getParameter("name");

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h2>查找到的翻译为 </h2>");
        writer.println("<p>用户要查找的汉字是： </p>" + wd);
        String eng = null;
        String juzi = null;
        try (Connection connection = getConnection()){
            String sql = "select eng,example from dic where cn = ? ";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1,wd);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()){
                         eng = resultSet.getString("eng");
                         juzi = resultSet.getString("example");
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        writer.println("<p>查询到的结果是： </p>" + eng);
        writer.println("<p>查询到的句子是： </p>" + juzi);


    }

        private static volatile DataSource dataSource = null;

        public static Connection getConnection() throws SQLException {
            if (dataSource == null){
                synchronized (FirstDemo.class){
                    if (dataSource == null){
                        MysqlDataSource mysqlDataSource = new MysqlDataSource();
                        mysqlDataSource.setServerName("127.0.0.1");
                        mysqlDataSource.setPort(3306);
                        mysqlDataSource.setUser("root");
                        mysqlDataSource.setPassword("root");
                        mysqlDataSource.setDatabaseName("dictionaries");
                        mysqlDataSource.setUseSSL(false);
                        mysqlDataSource.setCharacterEncoding("utf8");
                        dataSource = mysqlDataSource;
                    }
                }
            }
            return dataSource.getConnection();
        }

}
