
package com.example.wenwei.diycode_sdk.api.news.event;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;
import com.example.wenwei.diycode_sdk.api.news.bean.New;

public class CreateNewsEvent extends BaseEvent<New> {
    /**
     * @param uuid 唯一识别码
     */
    public CreateNewsEvent(@Nullable String uuid) {
        super(uuid);
    }

    /**
     * @param uuid 唯一识别码
     * @param code 网络返回码
     * @param aNew 实体数据
     */
    public CreateNewsEvent(@Nullable String uuid, @NonNull Integer code, @Nullable New aNew) {
        super(uuid, code, aNew);
    }
}
