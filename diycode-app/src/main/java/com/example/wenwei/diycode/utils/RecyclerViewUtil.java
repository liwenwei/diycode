package com.example.wenwei.diycode.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wenwei.recyclerview.adapter.singletype.SingleTypeAdapter;

/**
 * 当 RecyclerView 外围嵌套 ScrollView 时，将滚动事件交予上层处理
 */
public class RecyclerViewUtil {

    public static <T extends SingleTypeAdapter> void init(Context context, RecyclerView recyclerView, T adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }
}
