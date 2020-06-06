package com.android.androidlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.androidlearning.kotlin.KotlinActivity;
import com.android.androidlearning.learningcode.fragment.ViewInflateFragment;
import com.android.androidlearning.learningcode.ipc.IPCActivity;
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
        fView(R.id.btn_view_inflate);
        fView(R.id.btn_common);
        fView(R.id.btn_widget);
        fView(R.id.btn_kotlin);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_ipc) {
            startAct(IPCActivity.class);
        } else if (id == R.id.btn_contentprovider) {
            startAct(ActBookProvider.class);
        } else if (id == R.id.btn_view_event) {
            startAct(ActViewEvent.class);
        } else if (id == R.id.btn_recycleview) {
            startAct(ActRecycleView.class);
        } else if (id == R.id.btn_tmp) {
            startAct(TmpActivity.class);
        } else if (id == R.id.btn_view_inflate) {
            startFragment(new ViewInflateFragment());
        } else if (id == R.id.btn_common) {
            startAct(CommonActivity.class);
        } else if (id == R.id.btn_widget) {
            startAct(WidgetActivity.class);
        } else if (id == R.id.btn_kotlin) {
            startAct(KotlinActivity.class);
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

    private Fragment mCurFragment;

    protected void startFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.ll_main, fragment).commit();
        mCurFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        if (mCurFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(mCurFragment).commitAllowingStateLoss();
            mCurFragment = null;
        } else {
            super.onBackPressed();
        }

    }
}
