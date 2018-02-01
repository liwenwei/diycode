package com.example.wenwei.diycode.activity;


import android.view.View;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.BaseActivity;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.utils.AppUtil;
import com.example.wenwei.diycode.utils.Config;
import com.example.wenwei.diycode.utils.DataCleanManager;
import com.example.wenwei.diycode.utils.FileUtil;
import com.example.wenwei.diycode.utils.IntentUtil;

import java.io.File;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("设置");
        showCacheSize(holder);

        String versionName = AppUtil.getVersionName(this);
        holder.setText(R.id.app_version, versionName);

        if (mDiycode.isLogin()) {
            holder.get(R.id.user).setVisibility(View.VISIBLE);
        } else {
            holder.get(R.id.user).setVisibility(View.GONE);
        }

        holder.setOnClickListener(this, R.id.clear_cache, R.id.logout, R.id.about, R.id.contribute);
    }

    // 显示缓存大小
    private void showCacheSize(ViewHolder holder) {
        try {
            File cacheDir = new File(FileUtil.getExternalCacheDir(this));
            String cacheSize = DataCleanManager.getCacheSize(cacheDir);
            if (!cacheSize.isEmpty()) {
                holder.setText(R.id.cache_size, cacheSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                if (!mDiycode.isLogin())
                    return;
                mDiycode.logout();
                toastShort("退出成功");
                break;
            case R.id.clear_cache:
                DataCleanManager.deleteFolderFile(FileUtil.getExternalCacheDir(this), false);
                showCacheSize(getViewHolder());
                toastShort("清除缓存成功");
                break;
            case R.id.about:
                openActivity(AboutActivity.class);
                break;
            case R.id.contribute:
                IntentUtil.openAlipay(this);
                break;
        }
    }
}
