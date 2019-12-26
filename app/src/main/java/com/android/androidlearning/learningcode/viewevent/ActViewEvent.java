package com.android.androidlearning.learningcode.viewevent;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.androidlearning.BaseActivity;
import com.android.androidlearning.R;

/**
 * Created by xiezhaofei on 2019-11-13
 * <p>
 * Describe:
 */
public class ActViewEvent extends BaseActivity {

    private Fragment mCurFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();

    }

    private void initViews() {
        setContentView(R.layout.act_view_event);
        fView(R.id.btn_delivery_event, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new EventDeliveryFragment());
            }
        });
        fView(R.id.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().remove(mCurFragment).commit();
            }
        });
        fView(R.id.btn_sliding_conflict, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new SlidingConflictFragment());
            }
        });

    }

    private void startFragment(Fragment fragment) {
        mCurFragment = fragment;
        getSupportFragmentManager().beginTransaction().add(R.id.fra_container, fragment).commit();
    }


}
