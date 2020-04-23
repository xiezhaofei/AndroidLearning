package com.android.androidlearning.learningcode.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.androidlearning.R;

/**
 * Created by xiezhaofei on 2020-01-08
 * <p>
 * Describe:
 */
public class BaseFragment2 extends BaseFragment {
    FrameLayout mFraContainer;

    LinearLayout mItemContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.act_base;
    }

    @Override
    protected void initViews() {
        mFraContainer = findViewById(R.id.fra_container);
        mItemContainer = findViewById(R.id.ll_item);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    @Override
    protected void initValues(Bundle savedInstanceState) {

    }

    protected void addButton(String text, View.OnClickListener listener) {
        Button button = new Button(getContext());
        button.setText(text);
        button.setOnClickListener(listener);
        mItemContainer.addView(button, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    protected View getButton(String text) {
        int count = mItemContainer.getChildCount();
        View view = null;
        for (int i = 0; i < count; i++) {
            if (((TextView) mItemContainer.getChildAt(i)).getText().equals(text)) {
                view = mItemContainer.getChildAt(i);
                break;
            }
        }
        return view;
    }

    protected void removeButton(String text) {
        int count = mItemContainer.getChildCount();
        View view = null;
        for (int i = 0; i < count; i++) {
            if (((TextView) mItemContainer.getChildAt(i)).getText().equals(text)) {
                view = mItemContainer.getChildAt(i);
                break;
            }
        }
        mItemContainer.removeView(view);
    }

    protected void back() {

    }


}
