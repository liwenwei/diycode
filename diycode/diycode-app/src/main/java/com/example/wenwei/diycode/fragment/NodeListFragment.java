package com.example.wenwei.diycode.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.wenwei.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.example.wenwei.diycode.fragment.provider.TopicProvider;
import com.example.wenwei.diycode_sdk.api.topic.bean.Topic;
import com.example.wenwei.diycode_sdk.api.topic.event.GetTopicsListEvent;
import com.example.wenwei.recyclerview.adapter.multitype.HeaderFooterAdapter;

/**
 * 分类列表，用于定制首页内容
 * 考虑到节点列表一般不会变化，所以直接将数据封装在客户端中，可以直接查看，提高效率。
 */
public class NodeListFragment extends SimpleRefreshRecyclerFragment<Topic, GetTopicsListEvent> {
    private static String Key_Node_ID = "Key_Node_ID";
    private int mNodeId = 0;

    public static NodeListFragment newInstance(int nodeId) {
        Bundle args = new Bundle();
        args.putInt(Key_Node_ID, nodeId);

        NodeListFragment fragment = new NodeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        setLoadMoreEnable(false);
        setRefreshEnable(false);
        mNodeId = getArguments().getInt(Key_Node_ID, 0);
        loadMore();
    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView, HeaderFooterAdapter adapter) {
        adapter.register(Topic.class, new TopicProvider(getContext()));
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getTopicsList(null,mNodeId, offset, limit);
    }
}
