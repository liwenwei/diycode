package com.example.wenwei.diycode.fragment;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wenwei.diycode.fragment.base.RefreshRecyclerFragment;
import com.example.wenwei.diycode.fragment.bean.SiteItem;
import com.example.wenwei.diycode.fragment.bean.SitesItem;
import com.example.wenwei.diycode.fragment.provider.SiteProvider;
import com.example.wenwei.diycode.fragment.provider.SitesProvider;
import com.example.wenwei.diycode_sdk.api.sites.bean.Sites;
import com.example.wenwei.diycode_sdk.api.sites.event.GetSitesEvent;
import com.example.wenwei.diycode_sdk.log.Logger;
import com.example.wenwei.recyclerview.adapter.multitype.HeaderFooterAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页 sites 列表
 */
public class SitesListFragment extends RefreshRecyclerFragment<Sites, GetSitesEvent> {

    public static SitesListFragment newInstance() {
        SitesListFragment fragment = new SitesListFragment();
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        setLoadMoreEnable(true);
        List<Serializable> sitesList = mDataCache.getSitesItems();
        if (sitesList != null) {
            Logger.e("sites : " + sitesList.size());
            mAdapter.addDatas(sitesList);
            setLoadMoreEnable(false);
        } else {
            loadMore();
        }
    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView, HeaderFooterAdapter adapter) {
        mAdapter.register(SiteItem.class, new SiteProvider(getContext()));
        mAdapter.register(SitesItem.class, new SitesProvider(getContext()));
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (mAdapter.getFullDatas().get(position) instanceof SiteItem) ? 1 : 2;
            }
        });
        return layoutManager;
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getSites();
    }

    @Override
    protected void onRefresh(GetSitesEvent event, HeaderFooterAdapter adapter) {
        toast("刷新成功");
        convertData(event.getBean());
    }

    @Override
    protected void onLoadMore(GetSitesEvent event, HeaderFooterAdapter adapter) {
        toast("加载成功");
        convertData(event.getBean());
    }

    @Override
    protected void onError(GetSitesEvent event, String postType) {
        toast("获取失败");
    }

    /**
     * 转换数据
     *
     * @param sitesList Site list
     */
    private void convertData(final List<Sites> sitesList) {
        ArrayList<Serializable> items = new ArrayList<>();
        for (Sites sites : sitesList) {
            items.add(new SitesItem(sites.getName()));

            for (Sites.Site site : sites.getSites()) {
                items.add(new SiteItem(site.getName(), site.getUrl(), site.getAvatar_url()));
            }

            if (sites.getSites().size() % 2 == 1) {
                items.add(new SiteItem("", "", ""));
            }
        }

        mAdapter.clearDatas();
        mAdapter.addDatas(items);
        mDataCache.saveSitesItems(items);
        setLoadMoreEnable(false);
    }
}
