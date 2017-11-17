package com.example.wenwei.diycode_sdk.api.login.event;


import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.base.bean.State;
import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;

public class DeleteDevicesEvent extends BaseEvent<State> {
    public DeleteDevicesEvent(@NonNull String uuid) {
        super(uuid);
    }

    public DeleteDevicesEvent(@NonNull String uuid, @NonNull Integer code, @NonNull State state) {
        super(uuid, code, state);
    }
}
