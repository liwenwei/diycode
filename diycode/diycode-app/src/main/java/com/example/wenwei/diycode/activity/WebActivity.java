package com.example.wenwei.diycode.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.BaseActivity;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.utils.IntentUtil;
import com.just.agentweb.AgentWeb;

public class WebActivity extends BaseActivity implements View.OnClickListener {

    private AgentWeb mAgentWeb;

    public static final String URL = "web_url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnableSwipeGesture(true);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.container_webview);
        String url = getIntent().getStringExtra(URL);
        mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("");
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_share:
                IntentUtil.shareUrl(this, mAgentWeb.getWebCreator().get().getTitle(),
                        mAgentWeb.getWebCreator().get().getUrl());
                return true;
            case R.id.action_browser:
                if (mAgentWeb != null)
                    IntentUtil.openUrl(this, mAgentWeb.getWebCreator().get().getUrl());
                return true;
            default:
                return false;
        }
    }
}
