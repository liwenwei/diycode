package com.example.wenwei.diycode.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.activity.UserActivity;
import com.example.wenwei.diycode.base.glide.GlideImageGetter;
import com.example.wenwei.diycode.utils.HtmlParser;
import com.example.wenwei.diycode.utils.ImageUtil;
import com.example.wenwei.diycode.utils.TimeUtil;
import com.example.wenwei.diycode_sdk.api.topic.bean.TopicReply;
import com.example.wenwei.diycode_sdk.api.user.bean.User;
import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;
import com.example.wenwei.recyclerview.adapter.singletype.SingleTypeAdapter;

public class TopicReplyAdapter extends SingleTypeAdapter<TopicReply> {
    private Context mContext;

    public TopicReplyAdapter(@NonNull Context context) {
        super(context, R.layout.item_topic_reply);
        mContext = context;
    }

    /**
     * 在此处处理数据
     *
     * @param position 位置
     * @param holder   view holder
     * @param bean     数据
     */
    @Override
    public void convert(int position, RecyclerViewHolder holder, TopicReply bean) {
        final User user = bean.getUser();
        holder.setText(R.id.username, user.getLogin());
        holder.setText(R.id.time, TimeUtil.computePastTime(bean.getUpdated_at()));

        ImageView avatar = holder.get(R.id.avatar);
        ImageUtil.loadImage(mContext, user.getAvatar_url(), avatar);

        // TODO: 评论区代码高亮问题
        TextView content = holder.get(R.id.content);
        content.setText(Html.fromHtml(HtmlParser.removeP(bean.getBody_html()), new GlideImageGetter(mContext, content), null));

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UserActivity.class);
                intent.putExtra(UserActivity.USER, user);
                mContext.startActivity(intent);
            }
        }, R.id.avatar, R.id.username);
    }
}
