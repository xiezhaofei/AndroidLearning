package com.android.androidlearning;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by xiezhaofei on 2019-12-04
 * <p>
 * Describe:
 */
public class BaseActivity2 extends BaseActivity {

    FrameLayout mFraContainer;

    LinearLayout mItemContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_base);
        mFraContainer = findViewById(R.id.fra_container);
        mItemContainer = findViewById(R.id.ll_item);
        initViews();
    }

    protected void addButton(String text, View.OnClickListener listener) {
        Button button = new Button(this);
        button.setText(text);
        button.setOnClickListener(listener);
        mItemContainer.addView(button, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    protected void startFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fra_container, fragment).commit();
    }

    protected void initViews() {

    }


}
