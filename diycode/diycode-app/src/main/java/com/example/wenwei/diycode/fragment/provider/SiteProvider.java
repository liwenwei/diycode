package com.example.wenwei.diycode.fragment.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.fragment.bean.SiteItem;
import com.example.wenwei.diycode.utils.IntentUtil;
import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;
import com.example.wenwei.recyclerview.adapter.multitype.BaseViewProvider;


public class SiteProvider extends BaseViewProvider<SiteItem> {
    private Context mContext;

    public SiteProvider(@NonNull Context context) {
        super(context, R.layout.item_site);
        mContext = context;
    }

    /**
     * 在绑定数据时调用，需要用户自己处理
     *
     * @param holder ViewHolder
     * @param bean   数据
     */
    @Override
    public void onBindView(RecyclerViewHolder holder, final SiteItem bean) {
        if (bean.getName().isEmpty()) return;
        holder.setText(R.id.name, bean.getName());
        ImageView icon = holder.get(R.id.icon);
        Glide.with(mContext).load(bean.getAvatar_url()).into(icon);

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.openUrlWithinApp(mContext, bean.getUrl());
            }
        }, R.id.item);
    }
}