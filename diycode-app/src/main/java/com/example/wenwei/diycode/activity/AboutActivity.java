package com.example.wenwei.diycode.activity;


import android.view.View;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.BaseActivity;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.utils.IntentUtil;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("关于");
        holder.setOnClickListener(this, R.id.feed_back, R.id.github, R.id.contribute);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feed_back:
                IntentUtil.openUrl(mContext,"https://github.com/GcsSloop/diycode/issues/1");
                break;
            case R.id.github:
                IntentUtil.openUrl(mContext,"https://github.com/GcsSloop");
                break;
            case R.id.contribute:
                IntentUtil.openAlipay(mContext);
                break;
        }
    }
}
