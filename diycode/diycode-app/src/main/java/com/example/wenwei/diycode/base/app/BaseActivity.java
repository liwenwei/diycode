package com.example.wenwei.diycode.base.app;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.wenwei.diycode_sdk.api.Diycode;

public abstract class BaseActivity extends AppCompatActivity {

    protected Diycode mDiycode;
    protected RecyclerView.ViewHolder mViewHolder;
    private Toast mToast;

    /**
     * 初始化数据，调用位置在 initViews 之前
     */
    protected void initDatas() {
    }

    /**
     * 初始化 View， 调用位置在 initDatas 之后
     */
    protected abstract void initViews(ViewHolder holder, View root);

    private void initActionBar(ViewHolder holder){

    }
}
