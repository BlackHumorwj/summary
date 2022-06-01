package com.kotlin.for2021.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class InnerSwipeView extends SwipeRefreshLayout {
    public InnerSwipeView(@NonNull Context context) {
        super(context);
    }

    public InnerSwipeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 内部拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        final float y = ev.getY();
        final float x = ev.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //请求父控件不进行拦截DOWN事件
                getParent().requestDisallowInterceptTouchEvent(true);

                break;
            case MotionEvent.ACTION_MOVE:
                //请求父控件进行拦截
                getParent().requestDisallowInterceptTouchEvent(false);

                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return false;//配合内部拦截，不拦截
        }

        return super.onInterceptTouchEvent(ev);
    }
}
