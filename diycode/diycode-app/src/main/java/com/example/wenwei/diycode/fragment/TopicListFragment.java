package com.example.wenwei.diycode.fragment;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wenwei.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.example.wenwei.diycode.fragment.provider.TopicProvider;
import com.example.wenwei.diycode_sdk.api.topic.bean.Topic;
import com.example.wenwei.diycode_sdk.api.topic.event.GetTopicsListEvent;
import com.example.wenwei.recyclerview.adapter.multitype.HeaderFooterAdapter;

import java.util.List;

public class TopicListFragment extends SimpleRefreshRecyclerFragment<Topic, GetTopicsListEvent> {

    private boolean isFirstLaunch = true;

    public static TopicListFragment newInstance() {
        TopicListFragment fragment = new TopicListFragment();
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        // 优先从缓存中获取数据，如果是第一次加载则恢复滚动位置，如果没有缓存则从网络加载
        List<Object> topics = mDataCache.getTopicsListObj();
        if (topics != null && topics.size() > 0) {
            pageIndex = mConfig.getTopicListPageIndex();
            adapter.addDatas(topics);
            if (isFirstLaunch) {
                int lastPosition = mConfig.getTopicListLastPosition();
                mRecyclerView.getLayoutManager().scrollToPosition(lastPosition);
                isFirstAddFooter = false;
                isFirstLaunch = false;
            }
        } else {
            loadMore();
        }
    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView, HeaderFooterAdapter adapter) {
        adapter.register(Topic.class, new TopicProvider(context));
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // 存储PageIndex
        mConfig.saveTopicListPageIndex(pageIndex);

        // 存储 RecyclerView 滚动位置
        View view = mRecyclerView.getLayoutManager().getChildAt(0);
        int lastPosition = mRecyclerView.getLayoutManager().getPosition(view);
        mConfig.saveTopicListState(lastPosition, 0);
    }
}
