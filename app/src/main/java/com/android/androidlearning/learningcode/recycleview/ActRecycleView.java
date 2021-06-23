package com.android.androidlearning.learningcode.recycleview;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidlearning.BaseActivity;
import com.android.androidlearning.R;
import com.android.androidlearning.utils.ALLog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezhaofei on 2019-11-13
 * <p>
 * Describe:
 */
public class ActRecycleView extends BaseActivity {
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycleview);
        mRecyclerView = findViewById(R.id.recycler_view);

        Fruit fruit1 = new Fruit("苹果", 12);
        Fruit fruit2 = new Fruit("桃子", 9);
        Fruit fruit3 = new Fruit("西瓜", 29);
        Fruit fruit4 = new Fruit("樱桃", 20);
        final List<Fruit> list = new ArrayList<>();
        list.add(fruit1);
        list.add(fruit2);
        list.add(fruit3);
        list.add(fruit4);
        list.add(fruit1);
        list.add(fruit2);
        list.add(fruit3);
        list.add(fruit4);
        list.add(fruit1);
        list.add(fruit2);
        list.add(fruit3);
        list.add(fruit4);
        list.add(fruit1);
        list.add(fruit2);
        list.add(fruit3);
        list.add(fruit4);
        list.add(fruit1);
        list.add(fruit2);
        list.add(fruit3);
        list.add(fruit4);


        final FruitAdapter adapter = new FruitAdapter(null);

        final RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool() {
            @Override
            public void clear() {
                super.clear();
            }

            @Override
            public void setMaxRecycledViews(int viewType, int max) {
                super.setMaxRecycledViews(viewType, max);
            }

            @Override
            public int getRecycledViewCount(int viewType) {
                return super.getRecycledViewCount(viewType);
            }

            @Nullable
            @Override
            public RecyclerView.ViewHolder getRecycledView(int viewType) {
                return super.getRecycledView(viewType);
            }

            @Override
            public void putRecycledView(RecyclerView.ViewHolder scrap) {
                super.putRecycledView(scrap);
            }
        };
        Log.d(TAG, "POOL hashcode = " + pool.hashCode());


        pool.setMaxRecycledViews(0, 30);


        LinearLayoutManager layoutManager = new LinearLayoutManager(ActRecycleView.this);
        mRecyclerView.setLayoutManager(layoutManager);


        FruitAdapter adapter1 = new FruitAdapter(null);
        for (int i = 0; i < 30; i++) {
            pool.putRecycledView(adapter1.createViewHolder(mRecyclerView, 0));
        }
        //.....
        Log.d(TAG, "=======================================");

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setRecycledViewPool(pool);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.fruits = list;
                adapter.notifyDataSetChanged();


                RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
                refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh(RefreshLayout refreshlayout) {
                        refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                    }
                });
                refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(RefreshLayout refreshlayout) {
                        refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                    }
                });
            }
        }, 2000);

    }

    class FruitHolder extends RecyclerView.ViewHolder {
        TextView fruitName;
        TextView fruitPrice;


        public FruitHolder(@NonNull View itemView) {
            super(itemView);
            fruitName = itemView.findViewById(R.id.fruit_name);
            fruitPrice = itemView.findViewById(R.id.fruitprice);
        }
    }


    class FruitAdapter extends RecyclerView.Adapter<FruitHolder> {
        private List<Fruit> fruits = new ArrayList<Fruit>();

        public FruitAdapter(List<Fruit> fruits) {
            this.fruits = fruits;
        }

        public void setFruits(List<Fruit> fruits) {
            this.fruits = fruits;
        }

        @NonNull
        @Override
        public FruitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);

            ALLog.d(TAG, "onCreateViewHolder .... " /*+ ((RecyclerView) parent).getRecycledViewPool().getRecycledViewCount(viewType) */ + " hashcode = " + ((RecyclerView) parent).getRecycledViewPool().hashCode());
            return new FruitHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FruitHolder holder, int position) {
            Fruit fruit = fruits.get(position);
            holder.fruitPrice.setText("price:" + fruit.price);
            holder.fruitName.setText("name:" + fruit.name);
            ALLog.d(TAG, "onBindViewHolder");
        }

        @Override
        public int getItemCount() {
            if (fruits == null) {
                return 0;
            }
            return fruits.size();
        }

    }


}
