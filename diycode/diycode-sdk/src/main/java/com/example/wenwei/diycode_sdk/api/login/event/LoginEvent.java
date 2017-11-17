package com.example.wenwei.diycode_sdk.api.login.event;


import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;
import com.example.wenwei.diycode_sdk.api.login.bean.Token;

/**
 * 登录
 */
public class LoginEvent extends BaseEvent<Token> {

    /**
     * @param uuid 唯一识别码
     */
    public LoginEvent(@NonNull String uuid) {
        super(uuid);
    }

    /**
     * @param uuid  唯一识别码
     * @param code  网络返回码
     * @param token 实体数据
     */
    public LoginEvent(@NonNull String uuid, @NonNull Integer code, @NonNull Token token) {
        super(uuid, code, token);
    }
}
