package com.example.wenwei.recyclerview.adapter.multitype;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;

/**
 * ItemView 的管理者， 对View进行一些基本的操作
 *
 * @param <T>
 */
public abstract class BaseViewProvider<T> {

    private LayoutInflater mInflater;
    private int mLayoutId;
    protected Context mContext;

    public BaseViewProvider(@NonNull Context context, @NonNull @LayoutRes int layoutId) {
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mContext = context;
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(mLayoutId, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        onViewHolderIsCreated(viewHolder);
        return viewHolder;
    }

    /**
     * 当 ViewHolder 创建完成时调用
     *
     * @param holder ViewHolder
     */
    public void onViewHolderIsCreated(RecyclerViewHolder holder) {

    }

    /**
     * 在绑定数据时调用，需要用户自己处理
     *
     * @param holder ViewHolder
     * @param bean   数据
     */
    public abstract void onBindView(RecyclerViewHolder holder, T bean);
}
