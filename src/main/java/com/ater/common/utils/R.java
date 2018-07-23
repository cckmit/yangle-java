package com.ater.common.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author
 * @create 2017-04-01
 **/
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("resultCode", 200);
        put("resultMsg", "success");
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String resultMsg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, resultMsg);
    }

    public static R error(int resultCode, String resultMsg) {
        R r = new R();
        r.put("resultCode", resultCode);
        r.put("resultMsg", resultMsg);
        return r;
    }

    public static R ok(String resultMsg) {
        R r = new R();
        r.put("resultMsg", resultMsg);
        return r;
    }
    public static R ok1(String resultMsg,String data) {
        R r = new R();
        r.put("resultMsg", resultMsg);
        r.put("data", data);
        return r;
    }
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
