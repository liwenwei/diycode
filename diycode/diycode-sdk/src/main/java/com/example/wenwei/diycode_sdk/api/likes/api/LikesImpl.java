package com.example.wenwei.diycode_sdk.api.likes.api;


import android.content.Context;
import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.base.bean.State;
import com.example.wenwei.diycode_sdk.api.base.callback.BaseCallback;
import com.example.wenwei.diycode_sdk.api.base.impl.BaseImpl;
import com.example.wenwei.diycode_sdk.api.likes.event.LikeEvent;
import com.example.wenwei.diycode_sdk.api.likes.event.UnLikeEvent;
import com.example.wenwei.utils.UUIDGenerator;

public class LikesImpl extends BaseImpl<LikesService> implements LikesAPI {

    public LikesImpl(@NonNull Context context) {
        super(context);
    }

    /**
     * 赞
     *
     * @param obj_type 值范围["topic", "reply", "news"]
     * @param obj_id   唯一id
     * @see LikeEvent
     */
    @Override
    public String like(@NonNull String obj_type, @NonNull Integer obj_id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.like(obj_type, obj_id).enqueue(new BaseCallback<>(new LikeEvent(uuid)));
        return uuid;
    }

    /**
     * 取消之前的赞
     *
     * @param obj_type 值范围["topic", "reply", "news"]
     * @param obj_id   唯一id
     * @see UnLikeEvent
     */
    @Override
    public String unLike(@NonNull String obj_type, @NonNull Integer obj_id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.unLike(obj_type, obj_id).enqueue(new BaseCallback<State>(new UnLikeEvent(uuid)));
        return uuid;
    }
}
