package com.example.wenwei.diycode.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wenwei.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.example.wenwei.diycode.fragment.provider.TopicProvider;
import com.example.wenwei.diycode_sdk.api.topic.bean.Topic;
import com.example.wenwei.diycode_sdk.api.topic.event.GetTopicsListEvent;
import com.example.wenwei.diycode_sdk.log.Logger;
import com.example.wenwei.recyclerview.adapter.multitype.HeaderFooterAdapter;

import java.util.List;

public class TopicListFragment extends SimpleRefreshRecyclerFragment<Topic, GetTopicsListEvent> {
    private final String TAG = "TopicListFragment";

    private boolean isFirstLaunch = true;

    public static TopicListFragment newInstance() {
        Bundle args = new Bundle();
        TopicListFragment fragment = new TopicListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        // 优先从缓存中获取数据，如果是第一次加载则恢复滚动位置，如果没有缓存则从网络加载
        List<Object> topics = mDataCache.getTopicsListObj();
        if (topics != null && topics.size() > 0) {
            Logger.d(TAG, "topics : " + topics.size());
            adapter.addDatas(topics);
            if (isFirstLaunch) {
                isFirstAddFooter = false;
                isFirstLaunch = false;
            }
        } else {
            loadMore();
        }
    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView,
                                      HeaderFooterAdapter adapter) {
        adapter.register(Topic.class, new TopicProvider(getContext()));
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getTopicsList(null, null, offset, limit);
    }

    @Override
    protected void onRefresh(GetTopicsListEvent event, HeaderFooterAdapter adapter) {
        super.onRefresh(event, adapter);
        mDataCache.saveTopicsListObj(adapter.getDatas());
    }

    @Override
    protected void onLoadMore(GetTopicsListEvent event, HeaderFooterAdapter adapter) {
        // TODO 排除重复数据
        super.onLoadMore(event, adapter);
        mDataCache.saveTopicsListObj(adapter.getDatas());
        Logger.d(TAG, "onLoadMore");
    }
}
