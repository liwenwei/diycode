package com.example.wenwei.diycode.fragment.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.activity.TopicActivity;
import com.example.wenwei.diycode.activity.TopicContentActivity;
import com.example.wenwei.diycode.activity.UserActivity;
import com.example.wenwei.diycode.utils.TimeUtil;
import com.example.wenwei.diycode_sdk.api.topic.bean.Topic;
import com.example.wenwei.diycode_sdk.api.user.bean.User;
import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;
import com.example.wenwei.recyclerview.adapter.multitype.BaseViewProvider;


public class TopicProvider extends BaseViewProvider<Topic> {

    public TopicProvider(@NonNull Context context) {
        super(context, R.layout.item_topic);
    }

    /**
     * 在绑定数据时调用，需要用户自己处理
     *
     * @param holder ViewHolder
     * @param bean   数据
     */
    @Override
    public void onBindView(RecyclerViewHolder holder, final Topic bean) {
        final User user = bean.getUser();
        holder.setText(R.id.username, user.getLogin());
        holder.setText(R.id.node_name, bean.getNode_name());
        holder.setText(R.id.time, TimeUtil.computePastTime(bean.getUpdated_at()));
        holder.setText(R.id.title, bean.getTitle());

        // 加载头像
        ImageView imageView = holder.get(R.id.avatar);
        String url = user.getAvatar_url();
        String url2 = url;
        if (url.contains("diycode"))    // 添加判断，防止替换掉其他网站掉图片
            url2 = url.replace("large_avatar", "avatar");
        Glide.with(mContext)
                .load(url2)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);

        String state = "评论 " + bean.getReplies_count();
        holder.setText(R.id.state, state);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.avatar:
                    case R.id.username:
                        UserActivity.newInstance(mContext, user);
                        break;
                    case R.id.item:
                        TopicContentActivity.newInstance(mContext, bean);
                        break;
                    case R.id.node_name:
                        TopicActivity.newInstance(mContext, bean.getNode_id(), bean.getNode_name());
                        break;
                }
            }
        };

        holder.setOnClickListener(listener, R.id.avatar, R.id.username, R.id.item, R.id.node_name);
    }
}
