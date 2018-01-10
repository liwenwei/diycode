package com.example.wenwei.diycode.fragment.provider;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.activity.UserActivity;
import com.example.wenwei.diycode.activity.WebActivity;
import com.example.wenwei.diycode.utils.IntentUtil;
import com.example.wenwei.diycode.utils.TimeUtil;
import com.example.wenwei.diycode.utils.UrlUtil;
import com.example.wenwei.diycode_sdk.api.news.bean.New;
import com.example.wenwei.diycode_sdk.api.user.bean.User;
import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;
import com.example.wenwei.recyclerview.adapter.multitype.BaseViewProvider;

public class NewsProvider extends BaseViewProvider<New> {

    public NewsProvider(@NonNull Context context) {
        super(context, R.layout.item_news);
    }

    /**
     * 在绑定数据时调用，需要用户自己处理
     *
     * @param holder ViewHolder
     * @param bean   数据
     */
    @Override
    public void onBindView(RecyclerViewHolder holder, final New bean) {
        final User user = bean.getUser();
        holder.setText(R.id.username, user.getLogin());
        holder.setText(R.id.node_name, bean.getNode_name());
        holder.setText(R.id.time, TimeUtil.computePastTime(bean.getUpdated_at()));
        holder.setText(R.id.title, bean.getTitle());
        holder.setText(R.id.host_name, UrlUtil.getHost(bean.getAddress()));

        // 加载头像
        ImageView imageView = holder.get(R.id.avatar);
        String url = user.getAvatar_url();
        String url2 = url;
        if (url.contains("diycode"))   // 添加判断，防止替换掉其他网站掉图片
            url2 = url.replace("large_avatar", "avatar");
        Glide.with(mContext)
                .load(url2)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserActivity.newInstance(mContext, user);
            }
        }, R.id.avatar, R.id.username);

        holder.get(R.id.item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, WebActivity.class)
                        .putExtra(WebActivity.URL, bean.getAddress()));
            }
        });
    }
}
