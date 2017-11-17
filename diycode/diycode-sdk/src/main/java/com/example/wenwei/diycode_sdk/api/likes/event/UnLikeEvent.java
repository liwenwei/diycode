package com.example.wenwei.diycode_sdk.api.likes.event;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wenwei.diycode_sdk.api.base.bean.State;
import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;

public class UnLikeEvent extends BaseEvent<State> {
    /**
     * @param uuid 唯一识别码
     */
    public UnLikeEvent(@Nullable String uuid) {
        super(uuid);
    }

    /**
     * @param uuid  唯一识别码
     * @param code  网络返回码
     * @param state 实体数据
     */
    public UnLikeEvent(@Nullable String uuid, @NonNull Integer code, @Nullable State state) {
        super(uuid, code, state);
    }
}
