package com.example.wenwei.diycode.test;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.fragment.base.BaseFragment;
import com.example.wenwei.diycode_sdk.log.Logger;
import com.example.wenwei.recyclerview.adapter.multitype.HeaderFooterAdapter;

import java.util.ArrayList;
import java.util.List;


public class TestFragment extends BaseFragment implements View.OnClickListener {
    HeaderFooterAdapter mAdapter;
    List<Content> data;

    public static TestFragment newInstance() {
        Bundle args = new Bundle();
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh_recycler_test;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        holder.setOnClickListener(this, R.id.add_header, R.id.add_footer, R.id.add_content, R.id
                .remove_header, R.id.remove_footer, R.id.remove_content);

        SwipeRefreshLayout refreshLayout= holder.get(R.id.refresh_layout);
        refreshLayout.setEnabled(false);

        mAdapter = new HeaderFooterAdapter();
        // 注册类型
        mAdapter.register(Content.class, new ContentProvider(getContext()));

        data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Content content = new Content();
            content.text = "数据 - " + i;
            data.add(content);
        }

        RecyclerView recyclerView = holder.get(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_header:
                Logger.e("add_header");
                mAdapter.registerHeader(new Header(), new HeaderProvider(getContext()));
                break;
            case R.id.remove_header:
                Logger.e("remove_header");
                mAdapter.unRegisterHeader();
                break;
            case R.id.add_footer:
                Logger.e("add_footer");
                mAdapter.registerFooter(new Footer(), new FooterProvider(getContext()));
                break;
            case R.id.remove_footer:
                Logger.e("remove_footer");
                mAdapter.unRegisterFooter();
                break;
            case R.id.add_content:
                Logger.e("add_content");
                mAdapter.addDatas(data);
                break;
            case R.id.remove_content:
                Logger.e("remove_content");
                mAdapter.clearDatas();
                break;
        }
    }
}
