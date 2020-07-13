package com.bite.web.blog;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author tjy
 * @Date 2020/7/13 19:02
 */

public class JSONDemo {

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        JSONObject author = new JSONObject();
        author.put("id", 139);
        author.put("nickname", "鲁迅");
        object.put("id", 18);
        object.put("title", "论雷锋塔的倒掉");
        object.put("author", author);
        object.put("published_at", "2020-07-13 18:59:38");


        JSONArray array = new JSONArray();
        array.add(object);
        array.add(object.clone());

        System.out.println(array.toJSONString());
    }
}
