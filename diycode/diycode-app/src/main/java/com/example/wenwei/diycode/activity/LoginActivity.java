package com.example.wenwei.diycode.activity;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.BaseActivity;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.utils.IntentUtil;
import com.example.wenwei.diycode_sdk.api.Diycode;
import com.example.wenwei.diycode_sdk.api.login.event.LoginEvent;
import com.example.wenwei.diycode_sdk.log.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    EditText mUsername;
    EditText mPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("");
        mUsername = holder.get(R.id.username);
        mPassword = holder.get(R.id.password);

        holder.setOnClickListener(this, R.id.login, R.id.sign_up);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(LoginEvent event) {
        if (event.isOk()) {
            toastShort("登录成功");
            mDiycode.getMe();   // 获取个人信息，交给 MainActivity 处理
            finish();
        } else {
            String msg = "请重试";
            switch (event.getCode()) {
                case -1:
                    msg = "请检查网络连接";
                    break;
                case 400:
                case 401:
                    msg = "请检查用户名和密码是否正确";
                    break;
            }
            toastShort("登录失败：" + msg);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerKeyboardListener();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterKeyboardListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String name = mUsername.getText().toString();
                String pwd = mPassword.getText().toString();
                if (name.isEmpty() || pwd.isEmpty()) {
                    toastShort("Email/用户名或密码不能为空");
                    return;
                }
                mDiycode.login(name, pwd);
                break;
            case R.id.sign_up:
                IntentUtil.openUrl(this, "https://www.diycode.cc/account/sign_up");
                break;
        }
    }

    private void registerKeyboardListener() {
        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Logger.e("onGlobalLayout");
                if (isKeyboardShown(rootView)) {
                    Logger.e("软键盘弹起");
                    getViewHolder().get(R.id.span1).setVisibility(View.GONE);
                    getViewHolder().get(R.id.span2).setVisibility(View.GONE);
                } else {
                    Logger.e("软键盘未弹起");
                    getViewHolder().get(R.id.span1).setVisibility(View.INVISIBLE);
                    getViewHolder().get(R.id.span2).setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void unRegisterKeyboardListener() {
        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(null);
    }

    private boolean isKeyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics metrics = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * metrics.density;
    }
}
