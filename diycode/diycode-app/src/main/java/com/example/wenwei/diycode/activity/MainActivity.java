package com.example.wenwei.diycode.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wenwei.diycode.R;
import com.example.wenwei.diycode.base.app.BaseActivity;
import com.example.wenwei.diycode.base.app.ViewHolder;
import com.example.wenwei.diycode.fragment.NewsListFragment;
import com.example.wenwei.diycode.fragment.SitesListFragment;
import com.example.wenwei.diycode.fragment.TopicListFragment;
import com.example.wenwei.diycode.test.TextFragment;
import com.example.wenwei.diycode.utils.Config;
import com.example.wenwei.diycode.utils.DataCache;
import com.example.wenwei.diycode_sdk.api.login.event.LogoutEvent;
import com.example.wenwei.diycode_sdk.api.user.bean.User;
import com.example.wenwei.diycode_sdk.api.user.bean.UserDetail;
import com.example.wenwei.diycode_sdk.api.user.event.GetMeEvent;
import com.example.wenwei.diycode_sdk.log.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DataCache mCache;
    private Config mConfig;
    private int mCurrentPosition = 0;
    private TopicListFragment mFragment1;
    private NewsListFragment mFragment2;
    private SitesListFragment mFragment3;

    private boolean isToolbarFirstClick = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(ViewHolder holder, View root) {
        EventBus.getDefault().register(this);
        mCache = new DataCache(this);
        mConfig = Config.getSingleInstance();
        initMenu(holder);
        initViewPager(holder);
    }

    //--- viewpager adapter ------------------------------------------------------------------------

    private void initViewPager(ViewHolder holder) {
        ViewPager viewPager = holder.get(R.id.view_pager);
        TabLayout tabLayout = holder.get(R.id.tab_layout);
        viewPager.setOffscreenPageLimit(3); // 防止滑动到第三个页面时，第一个页面被销毁

        mFragment1 = TopicListFragment.newInstance();
        mFragment2 = NewsListFragment.newInstance();
        mFragment3 = SitesListFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] types = {"Topics", "News", "Sites", "Test"};

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return mFragment1;
                } else if (position == 1) {
                    return mFragment2;
                } else if (position == 2) {
                    return mFragment3;
                } else {
                    return TextFragment.newInstance(types[position]);
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return types[position];
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mCurrentPosition = mConfig.getMainViewPagerPosition();
        viewPager.setCurrentItem(mCurrentPosition);

        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 快速返回顶部
     */
    private void quickToTop() {
        switch (mCurrentPosition) {
            case 0:
                mFragment1.quickToTop();
                break;
            case 1:
                mFragment2.quickToTop();
                break;
            case 2:
                mFragment3.quickToTop();
                break;
        }
    }

    /**
     * 如果收到此状态说明用户已经登录成功了
     *
     * @param event GetMeEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(GetMeEvent event) {
        if (event.isOk()) {
            UserDetail me = event.getBean();
            mCache.saveMe(me);
            loadMenuData(); // 加载菜单数据
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogout(LogoutEvent event) {
        loadMenuData();
    }

    //--- menu -------------------------------------------------------------------------------------
    // 初始化菜单(包括侧边栏菜单和顶部菜单选项)
    private void initMenu(ViewHolder holder) {
        Toolbar toolbar = holder.get(R.id.toolbar);
        toolbar.setLogo(R.mipmap.logo_actionbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = holder.get(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigation = holder.get(R.id.nav_view);
        navigation.setNavigationItemSelectedListener(this);

        // 双击返回顶部
        final GestureDetector detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                quickToTop();               // 快速返回头部
                return super.onDoubleTap(e);
            }
        });

        toolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return false;
            }
        });

        toolbar.setOnClickListener(this);

        holder.setOnClickListener(this, R.id.fab);

        loadMenuData();
    }

    /**
     * 加载侧边栏菜单数据(与用户相关的)
     */
    private void loadMenuData() {
        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigation.getHeaderView(0);
        ImageView avatar = (ImageView) headerView.findViewById(R.id.nav_header_image);
        TextView userName = (TextView) headerView.findViewById(R.id.nav_header_name);
        TextView tagline = (TextView) headerView.findViewById(R.id.nav_header_tagline);

        if (mDiycode.isLogin()) {
            UserDetail me = mCache.getMe();
            if (me == null) {
                Logger.e("获取自己缓存失效");
                mDiycode.getMe();
                return;
            }

            userName.setText(me.getLogin());
            tagline.setText(me.getTagline());
            Glide.with(this).load(me.getAvatar_url()).into(avatar);
            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDetail me = mCache.getMe();
                    if (me == null) {
                        try {
                            me = mDiycode.getMeNow();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (me != null) {
                        User user = new User();
                        user.setId(me.getId());
                        user.setName(me.getName());
                        user.setLogin(me.getLogin());
                        user.setAvatar_url(me.getAvatar_url());
                        UserActivity.newInstance(MainActivity.this, user);
                    }
                }
            });
        } else {
            mCache.removeMe();
            userName.setText("(未登录)");
            tagline.setText("点击头像登陆");
            avatar.setImageResource(R.mipmap.ic_launcher);
            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivity(LoginActivity.class);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            openActivity(SettingActivity.class);
            return true;
        } else if (id == R.id.action_notification) {
            if (mDiycode.isLogin()) {
                openActivity(NotificationActivity.class);
            } else {
                openActivity(LoginActivity.class);
            }
			return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_post) {
            if (!mDiycode.isLogin()) {
                openActivity(LoginActivity.class);
                return true;
            }
            MyTopicActivity.newInstance(this, MyTopicActivity.InfoType.MY_TOPIC);
        } else if (id == R.id.nav_collect) {
            if (!mDiycode.isLogin()) {
                openActivity(LoginActivity.class);
                return true;
            }
            MyTopicActivity.newInstance(this, MyTopicActivity.InfoType.MY_COLLECT);
        } else if (id == R.id.nav_about) {
            openActivity(AboutActivity.class);
        } else if (id == R.id.nav_setting) {
            openActivity(SettingActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mConfig.saveMainViewPagerPosition(mCurrentPosition);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                if (isToolbarFirstClick) {
                    toastShort("双击标题栏快速返回顶部");
                    isToolbarFirstClick = false;
                }
                break;
            case R.id.fab:
                quickToTop();
                break;
        }
    }
}
