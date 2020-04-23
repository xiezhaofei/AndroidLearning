package com.android.androidlearning.learningcode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.androidlearning.learningcode.fragment.BaseFragment2;

/**
 * Created by xiezhaofei on 2020-03-23
 * <p>
 * Describe:
 */
public class TestBroadCastFragment extends BaseFragment2 {

    @Override
    protected void initViews() {
        super.initViews();
        addButton("注册本地广播", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("myAction");

                LocalBroadcastManager.getInstance(v.getContext()).registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        trace("onReceive:" + intent.getAction());
                    }
                }, intentFilter);
            }
        });
        addButton("发送本地广播", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("myAction");
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
            }
        });
    }
}
