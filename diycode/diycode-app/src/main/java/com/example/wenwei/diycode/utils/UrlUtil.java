package com.example.wenwei.diycode.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url 相关工具
 */
public class UrlUtil {
    public static boolean isUrlPrefix(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    /**
     * 判断后缀是不是图片类型的
     *
     * @param url url
     * @return
     */
    public static boolean isImageSuffix(String url) {
        return url.endsWith(".png")
                || url.endsWith(".PNG")
                || url.endsWith(".jpg")
                || url.endsWith(".JPG")
                || url.endsWith(".jpeg")
                || url.endsWith(".JPEG");
    }

    /**
     * 判断后缀是不是 GIF
     *
     * @param url url
     */
    public static boolean isGifSuffix(String url) {
        return url.endsWith(".gif")
                || url.endsWith(".GIF");
    }

    /**
     * 获取后缀名
     */
    public static String getSuffix(String url) {
        if ((url != null) && (url.length() > 0)) {
            int dot = url.lastIndexOf('.');
            if ((dot > -1) && (dot < (url.length() - 1))) {
                return url.substring(dot + 1);
            }
        }
        return url;
    }

    /**
     * 获取 mimeType
     */
    public static String getMimeType(String url) {
        if (url.endsWith(".png") || url.endsWith(".PNG")) {
            return "data:image/png;base64,";
        } else if (url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".JPG") || url.endsWith(".JPEG")) {
            return "data:image/jpg;base64,";
        } else if (url.endsWith(".gif") || url.endsWith(".GIF")) {
            return "data:image/gif;base64,";
        } else {
            return "";
        }
    }

    /**
     * 根据 url 获取 host name
     * http://www.gcssloop.com/ => www.gcssloop.com
     */
    public static String getHost(String url) {
        if (url == null || url.trim().equals("")) {
            return "";
        }
        String host = "";
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
        Matcher matcher = p.matcher(url);
        if (matcher.find()) {
            host = matcher.group();
        }
        return host;
    }
}
