package com.example.wenwei.diycode.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.wenwei.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.example.wenwei.diycode.fragment.provider.TopicProvider;
import com.example.wenwei.diycode_sdk.api.topic.bean.Topic;
import com.example.wenwei.diycode_sdk.api.user.event.GetUserCreateTopicListEvent;
import com.example.wenwei.recyclerview.adapter.multitype.HeaderFooterAdapter;

/**
 * 用户创建的 topic 列表
 */
public class UserCreateTopicFragment extends SimpleRefreshRecyclerFragment<Topic, GetUserCreateTopicListEvent> {
    private static String Key_User_Login_Name = "Key_User_Login_Name";
    private String loginName;

    public static UserCreateTopicFragment newInstance(String user_login_name) {
        Bundle args = new Bundle();
        args.putString(Key_User_Login_Name, user_login_name);
        UserCreateTopicFragment fragment = new UserCreateTopicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void initData(HeaderFooterAdapter adapter) {
        Bundle args = getArguments();
        loginName = args.getString(Key_User_Login_Name);
        loadMore();
    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView,
                                      HeaderFooterAdapter adapter) {
        adapter.register(Topic.class, new TopicProvider(context));
    }

    @NonNull @Override protected String request(int offset, int limit) {
        return mDiycode.getUserCreateTopicList(loginName, null, offset, limit);
    }
}
