package com.example.wenwei.swipebacklayout.library;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class SwipeBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableSwipeBack()) {
            SwipeBackLayout rootView = new SwipeBackLayout(this);
            rootView.bindActivity(this);
        }
    }

    protected boolean enableSwipeBack() {
        return true;
    }
}
