package com.example.wenwei.diycode_sdk.api.notifications.api;


import android.content.Context;
import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.base.callback.BaseCallback;
import com.example.wenwei.diycode_sdk.api.base.impl.BaseImpl;
import com.example.wenwei.diycode_sdk.api.notifications.event.DeleteAllNotificationEvent;
import com.example.wenwei.diycode_sdk.api.notifications.event.DeleteNotificationEvent;
import com.example.wenwei.diycode_sdk.api.notifications.event.GetNotificationUnReadCountEvent;
import com.example.wenwei.diycode_sdk.api.notifications.event.GetNotificationsListEvent;
import com.example.wenwei.diycode_sdk.api.notifications.event.MarkNotificationAsReadEvent;
import com.example.wenwei.utils.UUIDGenerator;

public class NotificationsImpl extends BaseImpl<NotificationsService> implements NotificationsAPI {

    public NotificationsImpl(@NonNull Context context) {
        super(context);
    }

    /**
     * 获取通知列表
     *
     * @param offset 偏移数值，默认值 0
     * @param limit  数量极限，默认值 20，值范围 1..150
     * @see GetNotificationsListEvent
     */
    @Override
    public String getNotificationsList(@NonNull Integer offset, @NonNull Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getNotificationsList(offset, limit)
                .enqueue(new BaseCallback<>(new GetNotificationsListEvent(uuid)));
        return uuid;
    }

    /**
     * 获得未读通知的数量
     *
     * @see GetNotificationUnReadCountEvent
     */
    @Override
    public String getNotificationUnReadCount() {
        String uuid = UUIDGenerator.getUUID();
        mService.getNotificationUnReadCount()
                .enqueue(new BaseCallback<>(new GetNotificationUnReadCountEvent(uuid)));
        return uuid;
    }

    /**
     * 将某些通知标记为已读
     *
     * @param ids id集合
     * @see MarkNotificationAsReadEvent
     */
    @Override
    public String markNotificationAsRead(int[] ids) {
        String uuid = UUIDGenerator.getUUID();
        mService.markNotificationAsRead(ids)
                .enqueue(new BaseCallback<>(new MarkNotificationAsReadEvent(uuid)));
        return uuid;
    }

    /**
     * 删除用户的某条通知
     *
     * @param id id
     * @see DeleteNotificationEvent
     */
    @Override
    public String deleteNotification(@NonNull Integer id) {
        String uuid = UUIDGenerator.getUUID();
        mService.deleteAllNotification()
                .enqueue(new BaseCallback<>(new DeleteNotificationEvent(uuid)));
        return uuid;
    }

    /**
     * 删除当前用户的所有通知
     *
     * @see DeleteAllNotificationEvent
     */
    @Override
    public String deleteAllNotification() {
        String uuid = UUIDGenerator.getUUID();
        mService.deleteAllNotification()
                .enqueue(new BaseCallback<>(new DeleteAllNotificationEvent(uuid)));
        return uuid;
    }
}
