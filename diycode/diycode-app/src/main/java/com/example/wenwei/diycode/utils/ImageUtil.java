package com.example.wenwei.diycode.utils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ImageUtil {

    /**
     * 加载图片
     *
     * @param context   Context
     * @param url       url
     * @param imageView ImageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        String url2 = url;
        if (url.contains("diycode"))
            url2 = url.replace("large_avatar", "avatar");
        Glide.with(context)
                .load(url2)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
}
