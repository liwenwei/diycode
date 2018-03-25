package com.example.wenwei.diycode_sdk.api.likes.event;

import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.base.bean.State;
import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;


public class LikeEvent extends BaseEvent<State> {
    /**
     * @param uuid 唯一识别码
     */
    public LikeEvent(@NonNull String uuid) {
        super(uuid);
    }

    /**
     * @param uuid  唯一识别码
     * @param code  网络返回码
     * @param state 实体数据
     */
    public LikeEvent(@NonNull String uuid, @NonNull Integer code, @NonNull State state) {
        super(uuid, code, state);
    }
}
