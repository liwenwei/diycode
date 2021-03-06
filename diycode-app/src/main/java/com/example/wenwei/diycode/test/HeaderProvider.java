package com.example.wenwei.diycode.test;


import android.content.Context;
import android.support.annotation.NonNull;

import com.example.wenwei.diycode.R;
import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;
import com.example.wenwei.recyclerview.adapter.multitype.BaseViewProvider;

public class HeaderProvider extends BaseViewProvider<Header> {
    public HeaderProvider(@NonNull Context context) {
        super(context, R.layout.item_test);
    }

    /**
     * 在绑定数据时调用，需要用户自己处理
     *
     * @param holder ViewHolder
     * @param bean   数据
     */
    @Override
    public void onBindView(RecyclerViewHolder holder, Header bean) {
        holder.setText(R.id.test_text, "-- 头部 --");
    }
}
