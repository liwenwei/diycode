
package com.example.wenwei.diycode_sdk.api.notifications.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;
import com.example.wenwei.diycode_sdk.api.notifications.bean.Count;

public class GetNotificationUnReadCountEvent extends BaseEvent<Count> {
    /**
     * @param uuid 唯一识别码
     */
    public GetNotificationUnReadCountEvent(@Nullable String uuid) {
        super(uuid);
    }

    /**
     * @param uuid  唯一识别码
     * @param code  网络返回码
     * @param count 实体数据
     */
    public GetNotificationUnReadCountEvent(@Nullable String uuid, @NonNull Integer code, @Nullable Count count) {
        super(uuid, code, count);
    }
}
