package com.example.wenwei.diycode_sdk.api.photo.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;
import com.example.wenwei.diycode_sdk.api.photo.bean.Photo;

public class UploadPhotoEvent extends BaseEvent<Photo> {
    /**
     * @param uuid 唯一识别码
     */
    public UploadPhotoEvent(@Nullable String uuid) {
        super(uuid);
    }

    /**
     * @param uuid  唯一识别码
     * @param code  网络返回码
     * @param photo 实体数据
     */
    public UploadPhotoEvent(@Nullable String uuid, @NonNull Integer code, @Nullable Photo photo) {
        super(uuid, code, photo);
    }
}
