package com.kotlin.for2021.view.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kotlin.for2021.R;
import com.kotlin.for2021.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RvActivity extends AppCompatActivity {

    private List<Star> starList;
    private RecyclerView mRecyclerView;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, RvActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        init();

        mRecyclerView = findViewById(R.id.rv_list);

        RecyclerViewUtil.init(this, mRecyclerView);

        //        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //        // 自定义分割线
        //        recyclerView.addItemDecoration(new MyItemDecoration(this));
        //        recyclerView.setAdapter(new StarAdapter(this, starList));
    }

    public void notifyAll(View view) {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public void notifyItem(View view) {
//        final Intent intent = NestedRvActivity.newInstance(this);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

        mRecyclerView.getAdapter().notifyItemChanged(0);
    }

    public void notifyRemove(View view) {
        mRecyclerView.getAdapter().notifyItemRemoved(0);
    }


    private void init() {
        starList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 20; j++) {
                if (i % 2 == 0) {
                    starList.add(new Star("何炅" + j, "快乐家族" + i));
                } else {
                    starList.add(new Star("汪涵" + j, "天天兄弟" + i));
                }
            }
        }
    }
}