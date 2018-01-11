package com.example.wenwei.diycode.markdownview;


import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.example.wenwei.diycode.base.app.BaseImageActivity;
import com.example.wenwei.diycode.utils.UrlUtil;
import com.example.wenwei.diycode_sdk.log.Logger;

import java.util.ArrayList;

public class WebImageListener {
    private Context mContext;
    private Class<? extends BaseImageActivity> mImageActivity;
    private ArrayList<String> mImages = new ArrayList<>();

    public WebImageListener(Context context, Class<? extends BaseImageActivity> imageActivity) {
        mContext = context;
        mImageActivity = imageActivity;
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    /**
     * 收集图片(当发现图片时会调用该方法)
     *
     * @param url 图片链接
     */
    @JavascriptInterface
    public void collectImage(final String url) {
        Logger.e("collect:" + url);
        if (UrlUtil.isGifSuffix(url)) {
            return;
        }
        if (!mImages.contains(url)) {
            mImages.add(url);
        }
    }

    /**
     * 图片被点击(图片被点击时调用该方法)
     *
     * @param url 图片链接
     */
    @JavascriptInterface
    public void onImageClicked(String url) {
        Logger.e("clicked:" + url);
        if (mImageActivity != null) {
            Intent intent = new Intent(mContext, mImageActivity);
            intent.putExtra(BaseImageActivity.CURRENT_IMAGE_URL, url);
            if (!UrlUtil.isGifSuffix(url)) {
                intent.putExtra(BaseImageActivity.ALL_IMAGE_URLS, mImages);
            }
            mContext.startActivity(intent);
        }
    }
}
