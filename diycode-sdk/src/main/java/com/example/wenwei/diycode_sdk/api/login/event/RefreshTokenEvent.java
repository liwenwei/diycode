package com.example.wenwei.diycode_sdk.api.login.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;
import com.example.wenwei.diycode_sdk.api.login.bean.Token;

public class RefreshTokenEvent extends BaseEvent<Token> {
    /**
     * @param uuid 唯一识别码
     */
    public RefreshTokenEvent(@Nullable String uuid) {
        super(uuid);
    }

    /**
     * @param uuid  唯一识别码
     * @param code  网络返回码
     * @param token 实体数据
     */
    public RefreshTokenEvent(@Nullable String uuid, @NonNull Integer code, @Nullable Token token) {
        super(uuid, code, token);
    }
}
