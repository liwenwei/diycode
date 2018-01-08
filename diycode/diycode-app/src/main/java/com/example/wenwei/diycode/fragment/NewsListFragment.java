package com.example.wenwei.diycode.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wenwei.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.example.wenwei.diycode.fragment.provider.NewsProvider;
import com.example.wenwei.diycode_sdk.api.news.bean.New;
import com.example.wenwei.diycode_sdk.api.news.event.GetNewsListEvent;
import com.example.wenwei.diycode_sdk.log.Logger;
import com.example.wenwei.recyclerview.adapter.multitype.HeaderFooterAdapter;

import java.util.List;

/**
 * 首页 news tab 列表
 */
public class NewsListFragment extends SimpleRefreshRecyclerFragment<New, GetNewsListEvent> {
    private final String TAG = "NewsListFragment";

    private boolean isFirstLaunch = true;

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        // 优先从缓存中获取数据，如果是第一次加载则恢复滚动位置，如果没有缓存则从网络加载
        List<Object> news = mDataCache.getNewsListObj();
        if (news != null && news.size() > 0) {
            Logger.d(TAG, "news : " + news.size());
            pageIndex = mConfig.getNewsListPageIndex();
            adapter.addDatas(news);
            if (isFirstLaunch) {
                int lastPosition = mConfig.getNewsListLastPosition();
                mRecyclerView.getLayoutManager().scrollToPosition(lastPosition);
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
        // TODO： Rename New as News
        adapter.register(New.class, new NewsProvider(getContext()));
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getNewsList(null, offset, limit);
    }

    @Override
    protected void onRefresh(GetNewsListEvent event, HeaderFooterAdapter adapter) {
        super.onRefresh(event, adapter);
        mDataCache.saveNewsListObj(adapter.getDatas());
    }

    @Override
    protected void onLoadMore(GetNewsListEvent event, HeaderFooterAdapter adapter) {
        // TODO: 排除重复数据
        super.onLoadMore(event, adapter);
        mDataCache.saveNewsListObj(adapter.getDatas());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // 存储 PageIndex
        mConfig.saveNewsListPageIndex(pageIndex);

        // 存储 RecyclerView 滚动位置
        View view = mRecyclerView.getLayoutManager().getChildAt(0);
        int lastPosition = mRecyclerView.getLayoutManager().getPosition(view);
        mConfig.saveNewsListScroll(lastPosition);
    }
}
