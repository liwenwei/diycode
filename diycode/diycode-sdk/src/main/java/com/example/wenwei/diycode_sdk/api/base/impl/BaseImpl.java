package com.example.wenwei.diycode_sdk.api.base.impl;


import com.example.wenwei.diycode_sdk.utils.CacheUtil;

import retrofit2.Retrofit;

/**
 * 实现类，具体实现在此处
 * @param <Service>
 */
public class BaseImpl<Service> {
    protected CacheUtil mCacheUtil;
    protected static Retrofit mRetrofit;
    protected Service mService;


}
