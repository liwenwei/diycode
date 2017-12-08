package com.example.wenwei.diycode.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.BaseActivity;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode_sdk.api.topic.bean.Topic;


/**
 * 查看不同分类的 Topic
 */
public class TopicActivity extends BaseActivity {
    private static String Key_Node_ID = "Key_Node_ID";
    private static String Key_Node_Name = "Key_Node_Name";

    public static void newInstance(Context context, int nodeId, String nodeName) {
        Intent intent = new Intent(context, TopicActivity.class);
        intent.putExtra(Key_Node_ID, nodeId);
        intent.putExtra(Key_Node_Name, nodeName);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        Intent intent = getIntent();
        int NodeId = intent.getIntExtra(Key_Node_ID, 0);
        String NodeName = intent.getStringExtra(Key_Node_Name);
        setTitle(NodeName);

        
    }
}
