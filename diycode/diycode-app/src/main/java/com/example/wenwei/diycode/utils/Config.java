package com.example.wenwei.diycode.utils;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.LruCache;

import com.example.wenwei.diycode_sdk.utils.ACache;

import java.io.Serializable;

/**
 * 用户设置
 */
public class Config {
    private static int N = 1024 * 1024;
    private volatile static Config mConfig;
    private static LruCache<String, Object> mLruCache = new LruCache<>(N);
    private static ACache mDiskCache;

    private Config(Context context) {
        mDiskCache = ACache.get(context, "config");
    }

    public static Config init(Context context) {
        if (null == mConfig) {
            synchronized (Config.class) {
                mConfig = new Config(context);
            }
        }
        return mConfig;
    }

    public static Config getSingleInstance() {
        return mConfig;
    }

    //--- 基础 -----------------------------------------------------------------------------------

    public <T extends Serializable> void saveData(@NonNull String key, @NonNull T value) {
        mLruCache.put(key, value);
        mDiskCache.put(key, value);
    }

    public <T extends Serializable> T getData(@NonNull String key, @NonNull T defaultValue) {
        T result = (T) mLruCache.get(key);
        if (result != null) {
            return result;
        }
        result = (T) mDiskCache.getAsObject(key);
        if (result != null) {
            mLruCache.put(key, result);
            return result;
        }
        return defaultValue;
    }
}
