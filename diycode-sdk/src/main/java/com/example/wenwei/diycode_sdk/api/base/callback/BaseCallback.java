package com.example.wenwei.diycode_sdk.api.base.callback;

import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseCallback<T> implements Callback<T> {
    protected BaseEvent<T> event;

    public <Event extends BaseEvent<T>> BaseCallback(@NonNull Event event) {
        this.event = event;
    }

    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call     回调
     * @param response 请求
     */
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            EventBus.getDefault().post(event.setEvent(response.code(), response.body()));
        } else {
            EventBus.getDefault().post(event.setEvent(response.code(), null));
        }
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call 回调
     * @param t    抛出的异常
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        EventBus.getDefault().post(event.setEvent(-1, null));
    }
}
