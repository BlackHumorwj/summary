package com.kotlin.for2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kotlin.for2021.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class RecyclerViewUtil {


    public static void init(Context context, RecyclerView recyclerView) {


        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setItemViewCacheSize(2);

        recyclerView.setAdapter(new RecyclerView.Adapter() {
            int position;

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                final View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom, parent, false);
                LogUtil.i("RecyclerViewUtil", "onCreateViewHolder" + (position++));
                return new MyHo(inflate);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                LogUtil.i("RecyclerViewUtil", "onBindViewHolder" + position);
                MyHo myHo = (MyHo) holder;
                TextView tvText = myHo.itemView.findViewById(R.id.tv_text);
                tvText.setText(position + "");

            }

            @Override
            public int getItemCount() {
                return 20;
            }
        });


    }

    static class MyHo extends RecyclerView.ViewHolder {

        public MyHo(@NonNull View itemView) {
            super(itemView);


        }
    }


}

