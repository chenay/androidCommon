package com.chenay.common.desgin.layout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Y.Chen5
 */
public class BaseViewPager extends ViewPager {
    private boolean isCanScroll = true;

    public BaseViewPager(@NonNull Context context) {
        super(context);
    }

    public BaseViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);
    }

    /**
     * 设置是否能够滑动翻页
     *
     * @param canScroll true 可以滑动翻页 , false 不能翻页
     */
    public void setCanScroll(boolean canScroll) {
        isCanScroll = canScroll;
    }
}
