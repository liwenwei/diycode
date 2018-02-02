package com.example.wenwei.diycode_sdk.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.login.bean.Token;

/**
 * 缓存工具类，用于缓存各类数据
 */
public class CacheUtil {

    private static final String TOKEN_KEY_NAME = "token";

    ACache cache;

    public CacheUtil(Context context) {
        cache = ACache.get(context);
    }

    //--- token ------------------------------------------------------------------------------------
    public void saveToken(@NonNull Token token) {
        if (null != cache) {
            cache.put(TOKEN_KEY_NAME, token);
        }
    }

    public Token getToken() {
        if (null != cache) {
            return (Token) cache.getAsObject(TOKEN_KEY_NAME);
        }
        return null;
    }

    public void clearToken() {
        if (null != cache) {
            cache.remove(TOKEN_KEY_NAME);
        }
    }
}
