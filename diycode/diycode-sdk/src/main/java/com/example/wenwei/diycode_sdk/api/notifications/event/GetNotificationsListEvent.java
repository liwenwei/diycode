
package com.example.wenwei.diycode_sdk.api.notifications.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;
import com.example.wenwei.diycode_sdk.api.notifications.bean.Notification;

import java.util.List;

public class GetNotificationsListEvent extends BaseEvent<List<Notification>> {

    /**
     * @param uuid 唯一识别码
     */
    public GetNotificationsListEvent(@Nullable String uuid) {
        super(uuid);
    }

    /**
     * @param uuid          唯一识别码
     * @param code          网络返回码
     * @param notifications 实体数据
     */
    public GetNotificationsListEvent(@Nullable String uuid, @NonNull Integer code, @Nullable List<Notification> notifications) {
        super(uuid, code, notifications);
    }
}
