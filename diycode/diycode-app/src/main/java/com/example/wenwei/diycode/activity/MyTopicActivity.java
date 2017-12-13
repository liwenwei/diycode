package com.example.wenwei.diycode.activity;


import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.BaseActivity;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.fragment.UserCollectionTopicFragment;
import com.example.wenwei.diycode.fragment.UserCreateTopicFragment;
import com.example.wenwei.diycode.utils.DataCache;
import com.example.wenwei.diycode_sdk.api.Diycode;
import com.example.wenwei.diycode_sdk.api.user.bean.UserDetail;

import java.io.IOException;

public class MyTopicActivity extends BaseActivity {
    private static final String TYPE = "type";
    private DataCache mCache;

    // 数据类型
    enum InfoType {
        MY_TOPIC, MY_COLLECT
    }

    private InfoType current_type = InfoType.MY_TOPIC;

    public static void newInstance(Context context, InfoType type) {
        Intent intent = new Intent(context, MyTopicActivity.class);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initDatas() {
        mDiycode = Diycode.getSingleInstance();
        mCache = new DataCache(this);

        Intent intent = getIntent();
        InfoType type = (InfoType) intent.getSerializableExtra(TYPE);
        if (type != null) {
            current_type = type;
        } else {
            current_type = InfoType.MY_TOPIC;
        }
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        if (!mDiycode.isLogin()) {
            toastShort("用户未登录");
            return;
        }

        if (mCache.getMe() == null) {
            try {
                UserDetail me = mDiycode.getMeNow();
                mCache.saveMe(me);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String userName = mCache.getMe().getLogin();

        // 初始化Fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (current_type == InfoType.MY_COLLECT) {
            setTitle("我的收藏");
            UserCollectionTopicFragment fragment1 = UserCollectionTopicFragment.newInstance(userName);
            transaction.add(R.id.fragment, fragment1);
        } else if (current_type == InfoType.MY_TOPIC) {
            setTitle("我的话题");
            UserCreateTopicFragment fragment2 = UserCreateTopicFragment.newInstance(userName);
            transaction.add(R.id.fragment, fragment2);
        }
        transaction.commit();
    }
}
