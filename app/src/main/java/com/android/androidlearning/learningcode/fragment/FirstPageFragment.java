package com.android.androidlearning.learningcode.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.androidlearning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezhaofei on 2019-12-25
 * <p>
 * Describe:
 */
public class FirstPageFragment extends Fragment {

    private GridView mGridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_firstpage, container, false);
        mGridView = view.findViewById(R.id.grid);
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("good");
        list.add("you");
        list.add("i");
        list.add("history");
        list.add("school");
        list.add("company");
        mGridView.setAdapter(new MAdapter(list));


        return view;
    }


    private static class MAdapter extends BaseAdapter {

        List<String> mList;

        public MAdapter(List<String> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_madapter, parent, false);
                Holder holder = new Holder();
                holder.title = view.findViewById(R.id.title);
                view.setTag(holder);
            }
            Holder holder = (Holder) view.getTag();
            ((TextView) holder.title).setText(mList.get(position));
            return view;
        }
    }

    private static class Holder {
        View title;
    }


}
