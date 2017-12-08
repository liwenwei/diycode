package com.example.wenwei.diycode.fragment.base;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.wenwei.diycode_sdk.api.base.event.BaseEvent;
import com.example.wenwei.recyclerview.adapter.multitype.HeaderFooterAdapter;
import com.example.wenwei.recyclerview.layoutmanager.SpeedyLinearLayoutManager;

import java.util.List;

public abstract class SimpleRefreshRecyclerFragment<T, Event extends BaseEvent<List<T>>> extends
        RefreshRecyclerFragment<T, Event> {

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        return new SpeedyLinearLayoutManager(getContext());
    }

    @Override
    protected void onRefresh(Event event, HeaderFooterAdapter adapter) {
        adapter.clearDatas();
        adapter.addDatas(event.getBean());
        toast("刷新成功");
    }

    @Override
    protected void onLoadMore(Event event, HeaderFooterAdapter adapter) {
        adapter.addDatas(event.getBean());
    }

    @Override
    protected void onError(Event event, String postType) {
        if (postType.equals(POST_LOAD_MORE)) {
            toast("加载更多失败");
        } else if (postType.equals(POST_REFRESH)) {
            toast("刷新数据失败");
        }
    }
}
