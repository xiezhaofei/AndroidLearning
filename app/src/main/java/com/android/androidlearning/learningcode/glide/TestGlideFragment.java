package com.android.androidlearning.learningcode.glide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.androidlearning.R;
import com.android.androidlearning.learningcode.fragment.BaseFragment;
import com.bumptech.glide.Glide;

/**
 * Created by xiezhaofei on 2020/4/23
 * <p>
 * Describe:
 */
public class TestGlideFragment extends BaseFragment {

    private ImageView imageView;


    @Override
    protected int getLayoutId() {
        return R.layout.fra_test_glide;
    }

    @Override
    protected void initViews() {
        imageView = findViewById(R.id.image);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getContext())
                        .load("https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg")
                        .into(imageView);
            }
        });
    }

    @Override
    protected void initValues(Bundle savedInstanceState) {

    }

}
