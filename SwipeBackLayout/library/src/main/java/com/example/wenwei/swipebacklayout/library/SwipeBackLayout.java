package com.example.wenwei.swipebacklayout.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;


public class SwipeBackLayout extends FrameLayout {

    private Activity mActivity;
    /**
     * 页面边缘阴影的宽度默认值
     */
    private final int LEFT_SHADOW_WIDTH = 16;
    /**
     * 页面边缘阴影的宽度
     */
    private int mLeftShadowWidth;
    /**
     * Activity 左便边缘的阴影图
     */
    private Drawable mLeftShadow;

    /**
     * 使用 Scroller 来处理手指释放后的滑动操作
     */
    private Scroller mScroller;

    private int mInterceptDownX;
    private int mLastInterceptX;
    private int mLastInterceptY;

    private int mTouchDownX;
    private int mLastTouchX;
    private int mLastTouchY;

    private boolean isConsumed = false;

    public SwipeBackLayout(Context context) {
        this(context, null);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mScroller = new Scroller(context);
        mLeftShadow = getResources().getDrawable(R.drawable.left_shadow);
        mLeftShadowWidth = (int) getResources().getDisplayMetrics().density * LEFT_SHADOW_WIDTH;
    }

    /**
     * 绑定Activity
     *
     * @param activity 要绑定的activity
     */
    public void bindActivity(Activity activity) {
        mActivity = activity;
        ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
        View child = decorView.getChildAt(0);
        decorView.removeView(child);
        addView(child);
        decorView.addView(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchDownX = x;
                mLastTouchX = x;
                mLastTouchY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - mLastTouchX;
                float deltaY = y - mLastTouchY;

                if (!isConsumed &&
                        mTouchDownX < (getWidth() / 10) &&
                        Math.abs(deltaX) > Math.abs(deltaY)) {
                    isConsumed = true;
                }

                if (isConsumed) {
                    int rightMovedX = mLastTouchX - x;
                    // 如果scroll value > 0，则表示向左滑动，反则，向右滑动
                    if (getScrollX() + rightMovedX >= 0) {
                        scrollTo(0, 0);
                    } else {
                        scrollBy(rightMovedX, 0);
                    }
                }

                mLastTouchX = x;
                mLastTouchY = y;
                break;
            case MotionEvent.ACTION_UP:
                isConsumed = false;
                mTouchDownX = mLastTouchX = mLastTouchY = 0;
                // 手指释放后，如果滑动距离超过屏幕的一半，则关闭 Activity ，否则，恢复原来状态
                if (-getScrollX() < getWidth() / 2) {
                    scrollBack();
                } else {
                    scrollClose();
                }
        }

        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                mInterceptDownX = x;
                mLastInterceptX = x;
                mLastInterceptY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastInterceptX;
                int deltaY = y - mLastInterceptY;
                // 手指处于屏幕边缘，且横向滑动距离大于纵向滑动距离时，拦截事件
                if (mInterceptDownX < (getWidth() / 10) &&
                        Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                mLastInterceptX = x;
                mLastInterceptY = y;
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                mInterceptDownX = mLastInterceptX = mLastInterceptY = 0;
                break;
        }

        return intercept;
    }

    /**
     * 滑动返回
     * <p>
     * startScroll()+invalidate()触发View的computeScroll()，在computeScroll()中让Scroller类去计算
     * 最新的坐标信息，拿到最新的坐标偏移信息后还是要调用View的scrollTo来实现滑动。
     */
    private void scrollBack() {
        int startX = getScrollX();
        int dx = -getScrollX();
        mScroller.startScroll(startX, 0, dx, 0, 300);
        invalidate();
    }

    /**
     * 滑动关闭
     * <p>
     * startScroll()+invalidate()触发View的computeScroll()，在computeScroll()中让Scroller类去计算
     * 最新的坐标信息，拿到最新的坐标偏移信息后还是要调用View的scrollTo来实现滑动。
     */
    private void scrollClose() {
        int startX = getScrollX();
        int dx = -getScrollX() - getWidth();
        mScroller.startScroll(startX, 0, dx, 0, 300);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        } else if (-getScrollX() >= getWidth()) {   // 如果向右滑动的距离大于当前Layout的宽度，就关闭activity
            mActivity.finish();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawShadow(canvas);
    }

    /**
     * 绘制滑动关闭activity时，左边边缘的阴影
     *
     * @param canvas 画布
     */
    private void drawShadow(Canvas canvas) {
        mLeftShadow.setBounds(0, 0, mLeftShadowWidth, getHeight());
        canvas.save();
        canvas.translate(-mLeftShadowWidth, 0);
        mLeftShadow.draw(canvas);
        canvas.restore();
    }
}
