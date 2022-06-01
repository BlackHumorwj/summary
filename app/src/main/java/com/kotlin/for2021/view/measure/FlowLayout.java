package com.kotlin.for2021.view.measure;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/22
 *     desc   : https://www.jianshu.com/p/9f90fea5e913
 *     version: 1.0
 * </pre>
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //确定View大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        //获取ViewGroup mode 和 Size
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int totalHeight = 0;
        int lineWidth = 0;
        //一行中最高的
        int lineMaxHeight = 0;
        //宽度中最宽的
        int lineMaxWidth = 0;


        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);

            final MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

            //todo 重点API 获取子View的 MeasureSpec
            final int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, layoutParams.width);
            final int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, layoutParams.height);

            //测量子View后就可以获取 子View的宽高
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);


            final int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            final int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;

            //不换行
            if (lineWidth + childWidth <= widthSize) {

                lineWidth += childWidth;

                //一行中最高的元素
                lineMaxHeight = Math.max(lineMaxHeight, childHeight);


            } else {//换行
                //高度增加
                totalHeight += lineMaxHeight;

                //换行后所有宽度中最宽的
                lineMaxWidth = Math.max(lineMaxWidth, lineWidth);

                //初始化
                lineWidth = childWidth;
            }

            //最后一个元素
            if (i == getChildCount() - 1) {
                //加入高度中
                totalHeight += childHeight;
            }

        }

        //父容器是 EXACTLY 使用父容器宽度
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : lineMaxWidth, heightMode == MeasureSpec.EXACTLY ? heightSize : totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();


        int lineWidth = 0;
        int maxLineHeight = 0;
        int totalHeight = 0;


        for (int i = 0; i < childCount; i++) {
            //获取子View的大小和尺寸
            final View child = getChildAt(i);
            final MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = layoutParams.width + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = layoutParams.height + layoutParams.topMargin + layoutParams.bottomMargin;


            //定义child四个顶点
            int cL = layoutParams.leftMargin;
            int cT = layoutParams.topMargin;
            int cR = layoutParams.leftMargin + child.getMeasuredWidth();
            int cB = layoutParams.topMargin + child.getMeasuredHeight();


            Log.e("xxx", layoutParams.width + "---" + child.getMeasuredWidth() + "----" + child.getWidth());

            if (lineWidth + childWidth <= getWidth()) {

                child.layout(cL + lineWidth, cT + totalHeight, cR + lineWidth, cB + totalHeight);

                lineWidth += childWidth;

                //maxLineHeight 改行最高的
                maxLineHeight = Math.max(maxLineHeight, childHeight);

            } else {//换行

                //宽度重置
                lineWidth = 0;
                //高度增加
                totalHeight += maxLineHeight;

                //布局子View
                child.layout(cL + lineWidth, cT + totalHeight, cR + lineWidth, cB + totalHeight);

                lineWidth = childWidth;

            }


        }
    }


    /**
     * 根据XML文件的设置的属性,返回一个支持Margin的LayoutParams
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
