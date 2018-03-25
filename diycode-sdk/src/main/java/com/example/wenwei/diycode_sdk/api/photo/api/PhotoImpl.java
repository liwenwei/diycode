package com.example.wenwei.diycode_sdk.api.photo.api;


import android.content.Context;
import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.base.callback.BaseCallback;
import com.example.wenwei.diycode_sdk.api.base.impl.BaseImpl;
import com.example.wenwei.diycode_sdk.api.photo.event.UploadPhotoEvent;
import com.example.wenwei.utils.UUIDGenerator;

import java.io.File;

public class PhotoImpl extends BaseImpl<PhotoService> implements PhotoAPI {
    public PhotoImpl(@NonNull Context context) {
        super(context);
    }

    /**
     * 上传图片,请使用 Multipart 的方式提交图片文件
     *
     * @param img_file 图片文件
     * @see UploadPhotoEvent
     */
    @Override
    public String uploadPhoto(@NonNull File img_file) {
        String uuid = UUIDGenerator.getUUID();
        mService.uploadPhoto(img_file).enqueue(new BaseCallback<>(new UploadPhotoEvent(uuid)));
        return uuid;
    }
}
