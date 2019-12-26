package com.android.androidlearning;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

/**
 * Created by xiezhaofei on 2019-11-08
 * <p>
 * Describe:
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {
    protected final String TAG = this.getClass().getSimpleName();

    protected void fView(int id) {
        findViewById(id).setOnClickListener(this);
    }

    protected void fView(int id, View.OnClickListener listener) {
        findViewById(id).setOnClickListener(listener);
    }


    @Override
    public void onClick(View v) {

    }

    protected void startAct(Class clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }


}
