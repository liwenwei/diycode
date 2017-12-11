package com.example.wenwei.diycode.fragment.provider;


import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.activity.TopicActivity;
import com.example.wenwei.diycode.activity.TopicContentActivity;
import com.example.wenwei.diycode.activity.UserActivity;
import com.example.wenwei.diycode.utils.HtmlUtil;
import com.example.wenwei.diycode.utils.ImageUtil;
import com.example.wenwei.diycode_sdk.api.notifications.bean.Notification;
import com.example.wenwei.diycode_sdk.api.user.bean.User;
import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;
import com.example.wenwei.recyclerview.adapter.multitype.BaseViewProvider;

public class NotificationsProvider extends BaseViewProvider<Notification> {
    // TODO: Extract the redundant variables
    private static String TYPE_NodeChanged = "NodeChanged";             // 节点变更
    private static String TYPE_TopicReply = "TopicReply";               // Topic 回复
    private static String TYPE_NewsReply = "Hacknews";                  // News  回复
    private static String TYPE_Mention = "Mention";                     // 有人提及
    private static String MENTION_TYPE_TopicReply = "Reply";            // - Topic 回复中提及
    private static String MENTION_TYPE_NewReply = "HacknewsReply";      // - News  回复中提及
    private static String MENTION_TYPE_ProjectReply = "ProjectReply";   // - 项目   回复中提及

    public NotificationsProvider(@NonNull Context context) {
        super(context, R.layout.item_notification);
    }


    @Override
    public void onBindView(RecyclerViewHolder holder, Notification bean) {
        final User actor = bean.getActor();
        String suffix = "";
        String desc = "";
        int topic_id = -1;
        if (bean.getType().equals(TYPE_TopicReply)) {
            suffix = "回复了话题： ";
            desc = bean.getReply().getTopic_title();
            topic_id = bean.getReply().getTopic_id();
        } else if (bean.getType().equals(TYPE_Mention)
                && bean.getMention_type().equals(MENTION_TYPE_TopicReply)) {
            suffix = "提到了你：";
            desc = bean.getMention().getBody_html();
            topic_id = bean.getMention().getTopic_id();
        }

        String type = actor.getLogin() + " " + suffix;

        ImageView imageView = holder.get(R.id.avatar);
        ImageUtil.loadImage(mContext, actor.getAvatar_url(), imageView);
        holder.setText(R.id.notification_type, type);

        Spanned result_desc = Html.fromHtml(HtmlUtil.removeP(desc));
        TextView text_desc = holder.get(R.id.desc);
        text_desc.setText(result_desc);

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserActivity.newInstance(mContext, actor);
            }
        }, R.id.avatar, R.id.notification_type);

        final int final_topic_id = topic_id;
        holder.get(R.id.item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicContentActivity.newInstance(mContext, final_topic_id);
            }
        });
    }
}
