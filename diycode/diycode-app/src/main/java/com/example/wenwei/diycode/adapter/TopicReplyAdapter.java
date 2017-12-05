package com.example.wenwei.diycode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode_sdk.api.topic.bean.TopicReply;
import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;
import com.example.wenwei.recyclerview.adapter.singletype.SingleTypeAdapter;

public class TopicReplyAdapter extends SingleTypeAdapter<TopicReply> {
    private Context mContext;

    public TopicReplyAdapter(@NonNull Context context) {
        super(context, R.layout.item_topic_reply);
        mContext = context;
    }

    @Override
    public void convert(int position, RecyclerViewHolder holder, TopicReply bean) {

    }
}
