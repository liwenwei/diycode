package com.example.wenwei.diycode_sdk.log;


import android.support.annotation.NonNull;
import android.util.Log;

public class Logger {

    private static Config sConfig;

    private Logger() {

    }

    public static Config init() {
        String DEFAULT_TAG = "DIYCODE-LOG";
        sConfig = new Config(DEFAULT_TAG);
        return sConfig;
    }

    public static Config init(@NonNull String tag) {
        sConfig = new Config(tag);
        return sConfig;
    }

    public static void v(String message) {
        log(Config.LEVEL_VERBOSE, sConfig.getTag(), message);
    }

    public static void v(String tag, String message) {
        log(Config.LEVEL_VERBOSE, tag, message);
    }

    public static void d(String message) {
        log(Config.LEVEL_DEBUG, sConfig.getTag(), message);
    }

    public static void d(String tag, String message) {
        log(Config.LEVEL_DEBUG, tag, message);
    }

    public static void i(String message) {
        log(Config.LEVEL_INFO, sConfig.getTag(), message);
    }

    public static void i(String tag, String message) {
        log(Config.LEVEL_INFO, tag, message);
    }

    public static void w(String message) {
        log(Config.LEVEL_WARN, sConfig.getTag(), message);
    }

    public static void w(String tag, String message) {
        log(Config.LEVEL_WARN, tag, message);
    }

    public static void e(String message) {
        log(Config.LEVEL_ERROR, sConfig.getTag(), message);
    }

    public static void e(String tag, String message) {
        log(Config.LEVEL_ERROR, tag, message);
    }

    private static void log(int level, String tag, String message) {
        if (sConfig.getLevel() == Config.LEVEL_NONE) {
            return;
        }

        if (level < sConfig.getLevel()) {
            return;
        }

        switch (level) {
            case Config.LEVEL_VERBOSE:
                Log.v(tag, message);
                break;
            case Config.LEVEL_DEBUG:
                Log.d(tag, message);
                break;
            case Config.LEVEL_INFO:
                Log.i(tag, message);
                break;
            case Config.LEVEL_WARN:
                Log.w(tag, message);
                break;
            case Config.LEVEL_ERROR:
                Log.e(tag, message);
                break;
        }
    }

}
