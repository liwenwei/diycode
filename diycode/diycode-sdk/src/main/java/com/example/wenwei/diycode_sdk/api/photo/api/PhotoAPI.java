package com.example.wenwei.diycode_sdk.api.photo.api;

import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.photo.event.UploadPhotoEvent;

import java.io.File;

public interface PhotoAPI {

    //--- photo ------------------------------------------------------------------------------------

    /**
     * 上传图片,请使用 Multipart 的方式提交图片文件
     *
     * @param img_file 图片文件
     * @see UploadPhotoEvent
     */
    String uploadPhoto(@NonNull File img_file);
}
