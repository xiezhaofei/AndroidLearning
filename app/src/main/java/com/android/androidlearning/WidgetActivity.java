package com.android.androidlearning;

import android.view.View;

import com.android.androidlearning.learningcode.fragment.TestStickerEditGroupFragment;
import com.android.androidlearning.learningcode.widget.TestCicleImageViewFragment;

/**
 * Created by xiezhaofei on 2020/4/29
 * <p>
 * Describe:
 */
public class WidgetActivity extends BaseActivity2 {
    @Override
    protected void initViews() {
        addButton("贴纸view", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestStickerEditGroupFragment());
            }
        });
        addButton("圆角图片", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestCicleImageViewFragment());
            }
        });

    }
}
