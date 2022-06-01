package com.kotlin.for2021.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.kotlin.for2021.util.LogUtil;

import androidx.annotation.Nullable;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DispatchChildView extends View {

    private final String TAG = getClass().getName();

    public DispatchChildView(Context context) {
        super(context);
    }

    public DispatchChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * true 消费事件
     * false 不消费，交由上一级onTouchEvent处理
     * super 此事件交给 onTouchEvent处理
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final boolean dispatchTouchEvent = super.dispatchTouchEvent(ev);
        LogUtil.i(TAG, "dispatchTouchEvent =" + dispatchTouchEvent);
        return dispatchTouchEvent;
    }


    /**
     * true 消费事件
     * false/super 返回上级处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final boolean onTouchEvent = super.onTouchEvent(event);
        LogUtil.i(TAG, "onTouchEvent =" + onTouchEvent);
        return onTouchEvent;
    }


}
