package com.android.androidlearning;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;

/**
 * Created by xiezhaofei on 2019-12-04
 * <p>
 * Describe:
 */
public abstract class BaseActivity2 extends BaseActivity {

    FrameLayout mFraContainer;

    LinearLayout mItemContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .init();
        setContentView(R.layout.act_base);
        mFraContainer = findViewById(R.id.fra_container);
        mItemContainer = findViewById(R.id.ll_item);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        initViews();
    }

    protected void addButton(String text, View.OnClickListener listener) {
        Button button = new Button(this);
        button.setText(text);
        button.setAllCaps(false);
        button.setOnClickListener(listener);
        mItemContainer.addView(button, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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

    private Fragment mCurFragment;

    protected void startFragment(Fragment fragment) {
        mCurFragment = fragment;
        getSupportFragmentManager().beginTransaction().add(R.id.fra_container, fragment).commit();
    }

    protected boolean removeCurFragment() {
        if (mCurFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(mCurFragment).commit();
            mCurFragment = null;
            return true;
        }
        return false;
    }

    protected abstract void initViews();

    protected void back() {

    }

}
