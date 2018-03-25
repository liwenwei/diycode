
package com.example.wenwei.diycode_sdk.api.news.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;
import com.example.wenwei.diycode_sdk.api.news.bean.NewReply;


public class CreateNewsReplyEvent extends BaseEvent<NewReply> {
    /**
     * @param uuid 唯一识别码
     */
    public CreateNewsReplyEvent(@Nullable String uuid) {
        super(uuid);
    }

    /**
     * @param uuid     唯一识别码
     * @param code     网络返回码
     * @param newReply 实体数据
     */
    public CreateNewsReplyEvent(@Nullable String uuid, @NonNull Integer code, @Nullable NewReply newReply) {
        super(uuid, code, newReply);
    }
}
