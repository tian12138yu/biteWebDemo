package com.bite.web.blog.model;

import com.bite.web.blog.util.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User {
    public int id;
    public String username;
    public String nickname;

    public static User register(String username, String password, String nickname) {
        User user = null;
        try (Connection c = DBUtil.getConnection()) {
            String sql = "INSERT INTO user (name, password,uname) VALUES (?, ?,?)";
            try (PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                s.setString(1, username);
                s.setString(2, password);
                s.setString(3,nickname);

                s.executeUpdate();

                try (ResultSet r = s.getGeneratedKeys()) {
                    if (!r.next()) {
                        return null;
                    }

                    user = new User();
                    user.id = r.getInt(1);
                    user.username = username;
                    user.nickname = nickname;
                    return user;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public static User login(String username,String password){
        User user = null;
        try (Connection c = DBUtil.getConnection()) {
            String sql = "select * from user where name = ? and password = ?";
            try (PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                s.setString(1, username);
                s.setString(2, password);
                try(ResultSet resultSet = s.executeQuery()){
                    while (resultSet.next()){
                        String id = resultSet.getString("id");
                        String name = resultSet.getString("name");
                        String nickname = resultSet.getString("uname");
                        user = new User();
                        user.nickname = nickname;
                        user.username = name;
                        user.id = Integer.parseInt(id);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public static void publish(int id, String title, String content) {
        try (Connection c = DBUtil.getConnection()) {
            String sql = "INSERT INTO articles (user_id, title,content,published_at) VALUES (?, ?,?,?)";
            try (PreparedStatement s = c.prepareStatement(sql)) {
                s.setInt(1, id);
                s.setString(2, title);
                s.setString(3, content);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(new Date());
                s.setString(4,format);
                s.executeUpdate();
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }

}
