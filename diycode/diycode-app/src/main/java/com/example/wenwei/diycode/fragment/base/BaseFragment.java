package com.example.wenwei.diycode.fragment.base;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.cache.Config;
import com.example.wenwei.diycode.cache.DataCache;
import com.example.wenwei.diycode_sdk.api.Diycode;

/**
 * 提供基础内容和生命周期控制
 */
public abstract class BaseFragment extends Fragment {
    private ViewHolder mViewHolder;     // View 管理
    // 数据
    protected Config mConfig;         // 配置(状态信息)
    protected Diycode mDiycode;       // 在线(服务器)
    protected DataCache mDataCache;   // 缓存(本地)

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConfig = Config.getSingleInstance();
        mDiycode = Diycode.getSingleInstance();
        mDataCache = new DataCache(getContext());
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initViews(ViewHolder holder, View root);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewHolder = new ViewHolder(inflater, container, getLayoutId());
        return mViewHolder.getRootView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews(mViewHolder, mViewHolder.getRootView());
    }

    protected void toast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
