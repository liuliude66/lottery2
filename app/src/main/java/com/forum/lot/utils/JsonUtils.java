package com.forum.lot.utils;

import com.google.gson.JsonParser;

/**
 * Json字符串辅助类 on 2018/1/27.
 */

public class JsonUtils {

    public static boolean isJson(String content) {
        try {
            new JsonParser().parse(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
