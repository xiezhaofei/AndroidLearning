package com.android.androidlearning.learningcode.listview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.LayoutRes;

import com.android.androidlearning.R;
import com.android.androidlearning.learningcode.fragment.BaseFragment;
import com.android.androidlearning.learningcode.viewevent.MListView;
import com.android.androidlearning.learningcode.viewevent.MTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezhaofei on 2020-03-22
 * <p>
 * Describe:
 */
public class TestListViewFragment extends BaseFragment {
    private MListView listView;
    private List<String> datas = new ArrayList<>();

    @Override
    protected @LayoutRes
    int getLayoutId() {
        return R.layout.fra_test_listview;
    }

    @Override
    protected void initViews() {
        listView = findViewById(R.id.listview);
        for (int i = 0; i < 100; i++) {
            datas.add("item : " + i);
        }
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public Object getItem(int position) {
                return datas.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                MTextView view = (MTextView) convertView;
                if (convertView == null) {
                    view = new MTextView(parent.getContext());
                }
                view.setText(datas.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trace("view click");
                    }
                });
                view.setHeight(100);
                return view;
            }


        };
        listView.setAdapter(adapter);
    }

    @Override
    protected void initValues(Bundle savedInstanceState) {

    }
}
