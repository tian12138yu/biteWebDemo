package com.bite.web.login;

/**
 * @Author tjy
 * @Date 2020/7/10 19:17
 */

import java.util.ArrayList;
import java.util.List;

public class Userr {
    String username;
    String password;

    public Userr(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 提交准备一些系统中的用户
    private static final List<Userr> USERR_LIST = new ArrayList<>();
    static {
        USERR_LIST.add(new Userr("peixinchen", "123"));
        USERR_LIST.add(new Userr("gaobo", "456"));
    }

    public static Userr login(String username, String password) {
        // 本质上就是一个查找
        for (Userr userr : USERR_LIST) {
            if (userr.username.equals(username) && userr.password.equals(password)) {
                return userr;
            }
        }
        return null;
    }

    public static void insert(String username, String password){
        USERR_LIST.add(new Userr(username,password));
    }
}

