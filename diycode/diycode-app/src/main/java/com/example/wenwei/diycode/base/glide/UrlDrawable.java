package com.example.wenwei.diycode.base.glide;


import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;

/**
 * 为 {@Link GlideImageGetter} 服务
 */
public class UrlDrawable extends Drawable implements Drawable.Callback {

    private Drawable mDrawable;

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mDrawable != null) {
            mDrawable.draw(canvas);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        if (mDrawable != null) {
            mDrawable.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (mDrawable != null) {
            mDrawable.setColorFilter(colorFilter);
        }
    }

    @Override
    public int getOpacity() {
        if (mDrawable != null) {
            return mDrawable.getOpacity();
        }
        return PixelFormat.UNKNOWN;
    }

    public void setDrawable(GlideDrawable drawable) {
        if (this.mDrawable != null) {
            this.mDrawable.setCallback(null);
        }
        drawable.setCallback(this);
        this.mDrawable = drawable;
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        if (getCallback() != null) {
            getCallback().invalidateDrawable(who);
        }
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        if (getCallback() != null) {
            getCallback().scheduleDrawable(who, what, when);
        }
    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        if (getCallback() != null) {
            getCallback().unscheduleDrawable(who, what);
        }
    }
}
