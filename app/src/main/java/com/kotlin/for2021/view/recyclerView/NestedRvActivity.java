package com.kotlin.for2021.view.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kotlin.for2021.R;
import com.kotlin.for2021.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class NestedRvActivity extends AppCompatActivity {

    private List<Star> starList;
    private MyRv mRecyclerView;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, NestedRvActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_rv);

        init();

        mRecyclerView = findViewById(R.id.rv_list);

        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        mRecyclerView.setMaxHeight(screenHeight);



//        MyRv rv = findViewById(R.id.rv_list2);
//        rv.setMaxHeight(screenHeight);
//        RecyclerViewUtil.init(this, rv);
        //        mRecyclerView.setNestedScrollingEnabled(false);
        //        mRecyclerView.setHasFixedSize(true);
        RecyclerViewUtil.init(this, mRecyclerView);

        //        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //        // 自定义分割线
        //        recyclerView.addItemDecoration(new MyItemDecoration(this));
        //        recyclerView.setAdapter(new StarAdapter(this, starList));

        Log.e("xxx", "onCreate");

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItem(v);
            }
        });

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("xxx", "onNewIntent");
    }

    public void notifyAll(View view) {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public void notifyItem(View view) {
        startActivity(RvActivity.newInstance(this));
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