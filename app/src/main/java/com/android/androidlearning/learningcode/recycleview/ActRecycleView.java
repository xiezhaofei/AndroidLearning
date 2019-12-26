package com.android.androidlearning.learningcode.recycleview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidlearning.BaseActivity;
import com.android.androidlearning.R;
import com.android.androidlearning.utils.ALLog;

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
        List<Fruit> list = new ArrayList<>();
        list.add(fruit1);
        list.add(fruit2);
        list.add(fruit3);
        list.add(fruit4);
        FruitAdapter adapter = new FruitAdapter(list);
//        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
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
        private List<Fruit> fruits;

        public FruitAdapter(List<Fruit> fruits) {
            this.fruits = fruits;
        }

        @NonNull
        @Override
        public FruitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
            ALLog.d(TAG, "onCreateViewHolder");
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
            return fruits.size();
        }
    }


}
