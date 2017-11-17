package com.example.wenwei.diycode_sdk.api.login.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wenwei.diycode_sdk.api.base.bean.State;
import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;

public class UpdateDevicesEvent extends BaseEvent<State> {
    public UpdateDevicesEvent(@Nullable String uuid) {
        super(uuid);
    }

    public UpdateDevicesEvent(@Nullable String uuid, @NonNull Integer code, @Nullable State state) {
        super(uuid, code, state);
    }
}
