package com.android.androidlearning.learningcode.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.androidlearning.R;
import com.android.androidlearning.learningcode.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class TestCicleImageViewFragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fra_cicle_imageview;
    }

    @Override
    protected void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("button position:" + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new MAdapter(list));
    }

    @Override
    protected void initValues(Bundle savedInstanceState) {

    }

    class MHolderView extends RecyclerView.ViewHolder {
        Button button;

        public MHolderView(@NonNull View itemView) {
            super(itemView);
            button = (Button) itemView;
        }
    }

    class MAdapter extends RecyclerView.Adapter<MHolderView> {

        List<String> list;

        public MAdapter(List<String> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public MHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MHolderView(new Button(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(@NonNull MHolderView holder, int position) {
            holder.button.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
