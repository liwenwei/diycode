package com.example.wenwei.diycode_sdk.api.notifications.api;


import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.notifications.event.*;

public interface NotificationsAPI {

    /**
     * 获取通知列表
     *
     * @param offset 偏移数值，默认值 0
     * @param limit  数量极限，默认值 20，值范围 1..150
     * @see GetNotificationsListEvent
     */
    String getNotificationsList(@NonNull Integer offset, @NonNull Integer limit);

    /**
     * 获得未读通知的数量
     *
     * @see GetNotificationUnReadCountEvent
     */
    String getNotificationUnReadCount();

    /**
     * 将某些通知标记为已读
     *
     * @param ids id集合
     * @see MarkNotificationAsReadEvent
     */
    String markNotificationAsRead(int[] ids);

    /**
     * 删除用户的某条通知
     *
     * @param id id
     * @see DeleteNotificationEvent
     */
    String deleteNotification(@NonNull Integer id);

    /**
     * 删除当前用户的所有通知
     *
     * @see DeleteAllNotificationEvent
     */
    String deleteAllNotification();

}
