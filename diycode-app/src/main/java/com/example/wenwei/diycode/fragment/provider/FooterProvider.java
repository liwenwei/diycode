package com.example.wenwei.diycode.fragment.provider;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.fragment.bean.Footer;
import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;
import com.example.wenwei.recyclerview.adapter.multitype.BaseViewProvider;

public class FooterProvider extends BaseViewProvider<Footer> {
    private static final String FOOTER_LOADING = "loading...";
    private static final String FOOTER_NORMAL = "-- end --";
    private static final String FOOTER_ERROR_A = "-- 获取失败 --";
    private static final String FOOTER_ERROR_B = "-- 失败，点击重试 --";
    private TextView footer;

    public FooterProvider(@NonNull Context context) {
        super(context, R.layout.item_footer);
    }

    @Override
    public void onViewHolderIsCreated(RecyclerViewHolder holder) {
        footer = holder.get(R.id.footer);
    }

    @Override
    public void onBindView(RecyclerViewHolder holder, Footer bean) {
        needLoadMore();
    }

    public void needLoadMore() {
    }

    public void setFooterLoading() {
        if (null == footer) return;
        footer.setText(FOOTER_LOADING);
    }

    public void setFooterNormal() {
        if (null == footer) return;
        footer.setText(FOOTER_NORMAL);
    }

    public void setFooterError(View.OnClickListener listener) {
        if (null == listener) return;
        if (null != listener) {
            footer.setText(FOOTER_ERROR_B);
            footer.setOnClickListener(listener);
            return;
        }
        footer.setText(FOOTER_ERROR_A);
    }
}
