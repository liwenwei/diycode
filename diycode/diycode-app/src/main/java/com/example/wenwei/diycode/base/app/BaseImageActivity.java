package com.example.wenwei.diycode.base.app;


import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

/**
 * 对数据进行预处理
 */
public abstract class BaseImageActivity extends BaseActivity {
    public static final String ALL_IMAGE_URLS = "all_images";
    public static final String CURRENT_IMAGE_URL = "current_image";

    protected static final String MODE_NORMAL = "normal";
    protected static final String MODE_ERROR = "error";
    protected String mCurrentMode = MODE_NORMAL;

    protected ArrayList<String> images = new ArrayList<>();     // 所有图片
    protected String current_image_url = null;                  // 当前图片
    protected int current_image_position = 0;                  // 当前图片位置

    @Override
    protected void initViews(ViewHolder holder, View root) {
        super.initDatas();

        // 初始化图片url和图片集合，保证两个数据都在
        Intent intent = getIntent();

        // 没有传递当前图片，设置模式为错误
        String imageUrl = intent.getStringExtra(CURRENT_IMAGE_URL);
        if (imageUrl == null || imageUrl.isEmpty()) {
            toastShort("没有传递图片链接");
            mCurrentMode = MODE_ERROR;
            return;
        }

        ArrayList<String> temp = intent.getStringArrayListExtra(ALL_IMAGE_URLS);
        if (temp == null || temp.size() <= 0) {
            // 记录当前图片，计算位置
            images.clear();
            images.add(imageUrl);
        } else if (temp.size() > 0) {
            // 如果图片集合大于0
            images = new ArrayList<>(temp);
        }

        if (!images.contains(imageUrl)) {
            images.add(imageUrl);
        }

        current_image_url = imageUrl;
        current_image_position = images.indexOf(imageUrl);

    }
}
