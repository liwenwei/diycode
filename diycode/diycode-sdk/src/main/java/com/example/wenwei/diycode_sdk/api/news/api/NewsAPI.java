package com.example.wenwei.diycode_sdk.api.news.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wenwei.diycode_sdk.api.news.event.CreateNewsEvent;
import com.example.wenwei.diycode_sdk.api.news.event.CreateNewsReplyEvent;
import com.example.wenwei.diycode_sdk.api.news.event.DeleteNewsReplyEvent;
import com.example.wenwei.diycode_sdk.api.news.event.GetNewsListEvent;
import com.example.wenwei.diycode_sdk.api.news.event.GetNewsNodesListEvent;
import com.example.wenwei.diycode_sdk.api.news.event.GetNewsRepliesListEvent;
import com.example.wenwei.diycode_sdk.api.news.event.GetNewsReplyEvent;
import com.example.wenwei.diycode_sdk.api.news.event.UpdateNewsReplyEvent;

public interface NewsAPI {

    /**
     * 获取 news 列表
     *
     * @param node_id 如果你需要只看某个节点的，请传此参数, 如果不传 则返回全部
     * @param offset  偏移数值，默认值 0
     * @param limit   数量极限，默认值 20，值范围 1..150
     * @see GetNewsListEvent
     */
    String getNewsList(@Nullable Integer node_id, @Nullable Integer offset, @Nullable Integer limit);

    /**
     * 创建一个 new (分享)
     *
     * @param title   标题
     * @param address 地址(网址链接)
     * @param node_id 节点 id
     * @see CreateNewsEvent
     */
    String createNews(@NonNull Integer title, @NonNull Integer address, @NonNull Integer node_id);

    /**
     * 获取 news 回帖列表
     *
     * @param id     id
     * @param offset 偏移数值 默认 0
     * @param limit  数量极限，默认值 20，值范围 1...150
     * @see GetNewsRepliesListEvent
     */
    String getNewsRepliesList(@NonNull int id, @Nullable Integer offset, @Nullable Integer limit);

    /**
     * 创建 news 回帖 (暂不可用, 没有api)
     *
     * @param id   id
     * @param body 回帖内容， markdown格式
     * @see CreateNewsReplyEvent
     */
    @Deprecated
    String createNewsReply(@NonNull int id, @NonNull Integer body);

    /**
     * 获取 news 回帖详情
     *
     * @param id id
     * @see GetNewsReplyEvent
     */
    String getNewsReply(@NonNull int id);

    /**
     * 更新 news 回帖
     *
     * @param id   id
     * @param body 回帖内容
     * @see UpdateNewsReplyEvent
     */
    String updateNewsReply(@NonNull int id, @NonNull String body);

    /**
     * 删除 news 回帖
     *
     * @param id id
     * @see DeleteNewsReplyEvent
     */
    String deleteNewsReply(@NonNull int id);

    /**
     * 获取 news 分类列表
     *
     * @see GetNewsNodesListEvent
     */
    String getNewsNodesList();

}