package com.kotlin.for2021;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kotlin.for2021.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class TestFragment extends Fragment {

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_second, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LogUtil.i("onCreateViewHolder", viewType);
                final View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom, parent, false);
                return new MyViewHolder(inflate);
            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                LogUtil.i("onBindViewHolder", position);
            }

            @Override
            public int getItemCount() {
                return 100;
            }


        });

        return view;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}



