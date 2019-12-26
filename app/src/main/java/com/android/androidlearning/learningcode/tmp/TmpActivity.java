package com.android.androidlearning.learningcode.tmp;

import android.view.View;

import com.android.androidlearning.BaseActivity2;
import com.android.androidlearning.learningcode.fragment.FirstPageFragment;
import com.android.androidlearning.learningcode.viewevent.EventDeliveryFragment;

/**
 * Created by xiezhaofei on 2019-12-04
 * <p>
 * Describe:
 */
public class TmpActivity extends BaseActivity2 {
    @Override
    protected void initViews() {
        super.initViews();
        addButton("图片", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new EventDeliveryFragment());
            }
        });
        addButton("gradview", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new FirstPageFragment());
            }
        });

    }
}
