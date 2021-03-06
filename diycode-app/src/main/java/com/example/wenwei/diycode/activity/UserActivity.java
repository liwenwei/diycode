package com.example.wenwei.diycode.activity;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.BaseActivity;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.utils.ImageUtil;
import com.example.wenwei.diycode.utils.RecyclerViewUtil;
import com.example.wenwei.utils.TimeUtil;
import com.example.wenwei.diycode_sdk.api.topic.bean.Topic;
import com.example.wenwei.diycode_sdk.api.user.bean.User;
import com.example.wenwei.diycode_sdk.api.user.bean.UserDetail;
import com.example.wenwei.diycode_sdk.api.user.event.GetUserCreateTopicListEvent;
import com.example.wenwei.diycode_sdk.api.user.event.GetUserEvent;
import com.example.wenwei.diycode_sdk.log.Logger;
import com.example.wenwei.recyclerview.adapter.base.RecyclerViewHolder;
import com.example.wenwei.recyclerview.adapter.singletype.SingleTypeAdapter;
import com.gcssloop.view.utils.DensityUtils;
import com.github.florent37.expectanim.ExpectAnim;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static com.example.wenwei.diycode.R.id.recycler_view;
import static com.github.florent37.expectanim.core.Expectations.alpha;
import static com.github.florent37.expectanim.core.Expectations.height;
import static com.github.florent37.expectanim.core.Expectations.leftOfParent;
import static com.github.florent37.expectanim.core.Expectations.sameCenterVerticalAs;
import static com.github.florent37.expectanim.core.Expectations.scale;
import static com.github.florent37.expectanim.core.Expectations.toRightOf;
import static com.github.florent37.expectanim.core.Expectations.topOfParent;


public class UserActivity extends BaseActivity implements View.OnClickListener {
    public static String USER = "user";
    public static String TYPE_DEFAULT = "default";
    public static String TYPE_USER_CREATE = "user_create";
    public static String TYPE_USER_REPLY = "user_reply";

    private ExpectAnim expectAnimMove;
    private SingleTypeAdapter<Topic> mAdapter;

    public static void newInstance(@NonNull Context context, @NonNull User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(USER, user);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        initUserInfo(holder);
        initRecyclerView(holder);
        initScrollAnimation(holder);
    }

    private void initUserInfo(ViewHolder holder) {
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(USER);
        if (null != user) {
            bindContent(holder, user.getLogin(), user.getName(), user.getAvatar_url());
            mDiycode.getUser(user.getLogin());
        }
    }

    private void initRecyclerView(ViewHolder holder) {
        mAdapter = new SingleTypeAdapter<Topic>(mContext, R.layout.item_topic) {
            /**
             * 在此处处理数据
             *
             * @param position 位置
             * @param holder   view holder
             * @param bean     数据
             */
            @Override
            public void convert(int position, RecyclerViewHolder holder, final Topic bean) {
                final User user = bean.getUser();
                holder.setText(R.id.username, user.getLogin());
                holder.setText(R.id.node_name, bean.getNode_name());
                holder.setText(R.id.time, TimeUtil.computePastTime(bean.getUpdated_at()));
                holder.setText(R.id.title, bean.getTitle());
                ImageView avatar = holder.get(R.id.avatar);
                ImageUtil.loadImage(mContext, user.getAvatar_url(), avatar);
                holder.get(R.id.item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TopicContentActivity.newInstance(mContext, bean);
                    }
                });
                holder.get(R.id.node_name).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TopicActivity.newInstance(mContext, bean.getNode_id(), bean.getNode_name());
                    }
                });
            }
        };

        RecyclerView recyclerView = holder.get(recycler_view);
        RecyclerViewUtil.init(mContext, recyclerView, mAdapter);
    }

    private void initScrollAnimation(ViewHolder holder) {
        NestedScrollView scrollView = holder.get(R.id.scroll_view);
        ImageView avatar = holder.get(R.id.avatar);
        TextView username = holder.get(R.id.username);
        View background = holder.get(R.id.background);

        this.expectAnimMove = new ExpectAnim()
                .expect(avatar)
                .toBe(
                        topOfParent().withMarginDp(13),
                        leftOfParent().withMarginDp(13),
                        scale(0.5f, 0.5f)
                )
                .expect(username)
                .toBe(
                        toRightOf(avatar).withMarginDp(16),
                        sameCenterVerticalAs(avatar),
                        alpha(0.5f)
                )
                .expect(background)
                .toBe(
                        height(DensityUtils.dip2px(mContext, 60)).withGravity(Gravity.START, Gravity.TOP)
                )
                .toAnimation();

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int
                    oldScrollX, int oldScrollY) {
                final float percent = (scrollY * 1f) / v.getMaxScrollAmount();
                expectAnimMove.setPercent(percent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUser(GetUserEvent event) {
        if (event.isOk()) {
            UserDetail user = event.getBean();
            bindContent(mViewHolder, user.getLogin(), user.getName(), user.getAvatar_url());
            getUserCreateTopicList(user.getLogin(), user.getTopics_count());
            Logger.e("返回 user 成功");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTopicList(GetUserCreateTopicListEvent event) {
        if (event.isOk()) {
            List<Topic> topics = event.getBean();
            mAdapter.addDatas(topics);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

    }

    private void getUserCreateTopicList(String loginName, int topicsCount) {
        mDiycode.getUserCreateTopicList(loginName, null, 0, topicsCount);
    }

    /**
     * 绑定页面上的内容
     *
     * @param login      登录用户名
     * @param name       昵称
     * @param avatar_url 头像链接
     */
    private void bindContent(ViewHolder holder, String login, String name, String avatar_url) {
        setTitle(login);
        if (null != holder) {
            holder.setText(R.id.username, name);
            holder.loadImage(mContext, avatar_url, R.id.avatar);
        }
    }
}
