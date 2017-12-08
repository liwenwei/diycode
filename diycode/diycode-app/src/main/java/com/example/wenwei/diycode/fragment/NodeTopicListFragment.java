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
 * 分类 topic 列表
 */
public class NodeTopicListFragment extends SimpleRefreshRecyclerFragment<Topic, GetTopicsListEvent> {
    private static String Key_Node_ID = "Key_Node_ID";
    private int mNodeId = 0;

    public static NodeTopicListFragment newInstance(int nodeId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Key_Node_ID, nodeId);

        NodeTopicListFragment fragment = new NodeTopicListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        mNodeId = getArguments().getInt(Key_Node_ID);
        loadMore();
    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView, HeaderFooterAdapter adapter) {
        mAdapter.register(Topic.class, new TopicProvider(getContext()));
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getTopicsList(null, mNodeId, offset, limit);
    }
}
