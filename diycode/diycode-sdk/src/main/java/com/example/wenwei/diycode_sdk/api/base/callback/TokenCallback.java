package com.example.wenwei.diycode_sdk.api.base.callback;

import android.support.annotation.NonNull;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;
import com.example.wenwei.diycode_sdk.api.login.bean.Token;
import com.example.wenwei.diycode_sdk.cache.CacheUtil;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 为 token 设置的 callback，相比于 BaseCallback，多了一个缓存。
 */
public class TokenCallback extends BaseCallback<Token> {
    private CacheUtil cacheUtil;

    public <Event extends BaseEvent<Token>> TokenCallback(@NonNull CacheUtil cacheUtil, @NonNull Event event) {
        super(event);
        this.cacheUtil = cacheUtil;
    }

    @Override
    public void onResponse(Call<Token> call, Response<Token> response) {
        if (response.isSuccessful()) {
            Token token = response.body();
            cacheUtil.saveToken(token);        // 请求成功后token缓存起来
            EventBus.getDefault().post(event.setEvent(response.code(), token));
        } else {
            EventBus.getDefault().post(event.setEvent(response.code(), null));
        }
    }
}
