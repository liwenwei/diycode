package com.example.wenwei.diycode.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.wenwei.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.example.wenwei.diycode.fragment.provider.NotificationsProvider;
import com.example.wenwei.diycode_sdk.api.notifications.bean.Notification;
import com.example.wenwei.diycode_sdk.api.notifications.event.GetNotificationsListEvent;
import com.example.wenwei.recyclerview.adapter.multitype.HeaderFooterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知列表
 */
public class NotificationsFragment extends SimpleRefreshRecyclerFragment<Notification,
        GetNotificationsListEvent> {

    private static String TYPE_TopicReply = "TopicReply";               // Topic 回复
    private static String TYPE_Mention = "Mention";                     // 有人提及
    /*
    private static String TYPE_NodeChanged = "NodeChanged";             // 节点变更
    private static String TYPE_NewsReply = "Hacknews";                  // News  回复
    */

    private static String MENTION_TYPE_TopicReply = "Reply";            // - Topic 回复中提及
    /*
    private static String MENTION_TYPE_NewReply = "HacknewsReply";      // - News  回复中提及
    private static String MENTION_TYPE_ProjectReply = "ProjectReply";   // - 项目   回复中提及
    */

    public static NotificationsFragment newInstance() {
        Bundle args = new Bundle();
        NotificationsFragment fragment = new NotificationsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        loadMore();
    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView,
                                      HeaderFooterAdapter adapter) {
        adapter.register(Notification.class, new NotificationsProvider(getContext()));
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getNotificationsList(offset, limit);
    }

    @Override
    protected void onRefresh(GetNotificationsListEvent event, HeaderFooterAdapter adapter) {
        List<Notification> data = clearData(event.getBean());
        adapter.clearDatas();
        adapter.addDatas(data);
        toast("刷新成功");
    }

    @Override
    protected void onLoadMore(GetNotificationsListEvent event, HeaderFooterAdapter adapter) {
        List<Notification> data = clearData(event.getBean());
        adapter.addDatas(data);
    }

    /**
     * 清洗数据，主要清洗对象
     * 1. HackNew 的回复 type = Hacknews
     * 2. HackNew 的提及 type = Mention, mention_type = HacknewsReply
     * 3. Project 的提及 type = Mention, mention_type = ProjectReply
     * <p>
     * 保留数据
     * 1. Topic 的回复 type = TopicReply
     * 2. Topic 的提及 type = Mention, mention_type = Reply
     */
    public List<Notification> clearData(List<Notification> datas) {
        List<Notification> clearDatas = new ArrayList<>();
        for (Notification data : datas) {
            if (data.getType().equals(TYPE_TopicReply) ||
                    (data.getType().equals(TYPE_Mention) && data.getMention_type().equals
                            (MENTION_TYPE_TopicReply))) {
                clearDatas.add(data);
            }
        }
        return clearDatas;
    }
}
