package com.kotlin.for2021.view.recyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private int headerHeight = 100;
    private Paint mPaint = new Paint();
    private int divideHeight = 10;

    public MyItemDecoration(Context context) {
        mPaint.setColor(Color.GREEN);
    }


    /*
    吸顶效果思路
    1、通过offset设置顶部空出较大空间
    2、通过onDraw绘制吸顶显示的文字
    3、通过onDrawOver绘制吸顶的的文字、并设置
    */


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //通过onDraw绘制吸顶显示的文字

        final int childCount = parent.getChildCount();

        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter adapter = (StarAdapter) parent.getAdapter();

            final int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();


            for (int i = 0; i < childCount; i++) {
                //获取屏幕上的View
                final View child = parent.getChildAt(i);
                final int position = parent.getChildAdapterPosition(child);
                //Header
                if (adapter.isGourpHeader(position)) {

                    final String groupName = adapter.getGroupName(position);

                    c.drawText(groupName, 1, 2, mPaint);

                } else {

                    //绘制分割线，坐标位置
                    c.drawRect(left, child.getTop() - divideHeight, right, child.getTop(), mPaint);
                }


            }
        }

    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter adapter = (StarAdapter) parent.getAdapter();

            //parent.getChildAdapterPosition();
            //parent.getChildLayoutPosition(); 会有个延迟，如果插入item有动画效果，只有等动画执行完才更新
            //parent.getChildCount() //获取可见的item 数量

            //当前View的位置
            final int position = parent.getChildAdapterPosition(view);
            if (adapter.isGourpHeader(position)) {
                outRect.top = headerHeight;
            } else {
                outRect.top = divideHeight;
            }


        }


    }

}
