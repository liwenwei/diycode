package com.example.wenwei.diycode.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.BaseActivity;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.fragment.NotificationsFragment;

/**
 * 查看通知
 */
public class NotificationActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("通知");
        NotificationsFragment fragment = NotificationsFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragment, fragment)
                .commit();
    }
}
