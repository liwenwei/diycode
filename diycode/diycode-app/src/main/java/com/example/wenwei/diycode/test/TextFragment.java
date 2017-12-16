package com.example.wenwei.diycode.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.fragment.base.BaseFragment;

public class TextFragment extends BaseFragment {
    private static final String TYPE = "type";

    public static TextFragment newInstance(@NonNull String type) {
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        TextFragment fragment = new TextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_text;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        String text = getArguments().getString(TYPE);
        holder.setText(R.id.text, "Gcs:" + text);
    }
}
