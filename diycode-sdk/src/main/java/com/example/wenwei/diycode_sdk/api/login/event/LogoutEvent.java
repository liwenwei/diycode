package com.example.wenwei.diycode_sdk.api.login.event;

import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;

/**
 * 退出账号
 */
public class LogoutEvent extends BaseEvent<String> {

    /**
     * @param uuid 唯一识别码
     */
    public LogoutEvent(@NonNull String uuid) {
        super(uuid);
    }

    /**
     * @param uuid 唯一识别码
     * @param code 网络返回码
     * @param s    实体数据
     */
    public LogoutEvent(@NonNull String uuid, @NonNull Integer code, @NonNull String s) {
        super(uuid, code, s);
    }
}
