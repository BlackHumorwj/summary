package com.kotlin.for2021.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.kotlin.for2021.util.LogUtil;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/26
 *     desc   : https://www.jianshu.com/p/fc0590afb1bf
 *     version: 1.0
 * </pre>
 */

public class DispatchViewGroup extends ViewGroup {



    private final String TAG = getClass().getName();

    public DispatchViewGroup(Context context) {
        super(context);
    }

    public DispatchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            child.layout(getLeft(), getTop(), getLeft() + child.getWidth(), getTop() + child.getHeight());
        }
    }


    //dispatchTouchEvent 处决事件
    // true 事件消费
    // false 事件终止分发，返回上一级onTouchEvent()方法
    // super 事件继续分发给子View

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final boolean dispatchTouchEvent = super.dispatchTouchEvent(ev);
        LogUtil.i(TAG, "dispatchTouchEvent =" + dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    /**
     * onInterceptTouchEvent 拦截事件
     * true 拦截事件->  自己onTouchEvent 进行处理
     * false/super 交给下一级View dispatchTouchEvent处理
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final boolean interceptTouchEvent = super.onInterceptTouchEvent(ev);
        LogUtil.i(TAG, "onInterceptTouchEvent = " + interceptTouchEvent);
        return interceptTouchEvent;
    }

    /**
     * onTouchEvent 事件处理
     * true 消费此事件
     * false/super 此事件返回上一级的onTouchEvent处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final boolean onTouchEvent = super.onTouchEvent(event);
        LogUtil.i(TAG, "onTouchEvent = " + onTouchEvent);
        return onTouchEvent;
    }


}
