package com.android.androidlearning.learningcode.fragment;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by xiezhaofei on 2020-01-08
 * <p>
 * Describe:
 */
public class ViewInflateFragment extends BaseFragment2 {

    @Override
    protected void initViews() {
        super.initViews();
        addButton("查看inflate factory", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater.Factory factory = LayoutInflater.from(getContext()).getFactory();
                trace(factory.toString());
                trace(getContext().toString());
                trace(LayoutInflater.from(getContext()).getFactory2().toString());
            }
        });
    }
}
