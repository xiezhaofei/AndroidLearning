package com.android.androidlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.androidlearning.learningcode.ipc.contentprovider.ActBookProvider;
import com.android.androidlearning.learningcode.recycleview.ActRecycleView;
import com.android.androidlearning.learningcode.tmp.TmpActivity;
import com.android.androidlearning.learningcode.viewevent.ActViewEvent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fView(R.id.btn_ipc);
        fView(R.id.btn_contentprovider);
        fView(R.id.btn_view_event);
        fView(R.id.btn_recycleview);
        fView(R.id.btn_tmp);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_ipc) {

        } else if (id == R.id.btn_contentprovider) {
            startAct(ActBookProvider.class);
        } else if (id == R.id.btn_view_event) {
            startAct(ActViewEvent.class);
        } else if (id == R.id.btn_recycleview) {
            startAct(ActRecycleView.class);
        } else if (id == R.id.btn_tmp) {
            startAct(TmpActivity.class);
        }
    }

    private void fView(int id) {
        findViewById(id).setOnClickListener(this);
    }

    private void startAct(Class clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        intent.putExtra("referid", "12345");
        intent.putExtra("name", "dhdhhd");
        intent.putExtra("age", 11);
        startActivity(intent);
    }
}
