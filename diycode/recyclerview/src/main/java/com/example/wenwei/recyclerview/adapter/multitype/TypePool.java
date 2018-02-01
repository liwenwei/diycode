package com.example.wenwei.recyclerview.adapter.multitype;


import android.support.annotation.NonNull;

import java.util.List;

/**
 * 类型池
 */
public interface TypePool {

    void register(@NonNull Class<?> clazz, @NonNull BaseViewProvider provider);

    int indexOf(@NonNull final Class<?> clazz);

    List<BaseViewProvider> getProviders();

    BaseViewProvider getProviderByIndex(int index);

    <T extends BaseViewProvider> T getProviderByClass(@NonNull final Class<?> clazz);
}
