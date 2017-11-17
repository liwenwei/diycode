package com.example.wenwei.diycode_sdk.api.likes.api;


import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.likes.event.*;

public interface LikesAPI {


    /**
     * 赞
     *
     * @param obj_type 值范围["topic", "reply", "news"]
     * @param obj_id   唯一id
     * @see LikeEvent
     */
    String like(@NonNull String obj_type, @NonNull Integer obj_id);


    /**
     * 取消之前的赞
     *
     * @param obj_type 值范围["topic", "reply", "news"]
     * @param obj_id   唯一id
     * @see UnLikeEvent
     */
    String unLike(@NonNull String obj_type, @NonNull Integer obj_id);

}
