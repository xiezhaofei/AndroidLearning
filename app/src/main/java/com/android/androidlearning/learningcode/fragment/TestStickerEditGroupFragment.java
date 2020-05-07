package com.android.androidlearning.learningcode.fragment;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.androidlearning.R;
import com.android.androidlearning.widget.StickerEditGroup;

/**
 * Created by xiezhaofei on 2020/4/29
 * <p>
 * Describe:
 */
public class TestStickerEditGroupFragment extends BaseFragment {
    private StickerEditGroup mStickerEditGroup;
    private FrameLayout mOuterContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.fra_test_sticker_edit_group;
    }

    @Override
    protected void initViews() {
        mStickerEditGroup = findViewById(R.id.sticker_edit_group);
        mOuterContainer = findViewById(R.id.fl_outer_container);

        mStickerEditGroup.setShowViewsContainer(mOuterContainer);
        mStickerEditGroup.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(R.drawable.ic_launcher_round);
                mStickerEditGroup.addSticker(imageView, 0.2, 0.2, 0.3, 0.3, 0f);
            }
        }, 1000);
    }

    @Override
    protected void initValues(Bundle savedInstanceState) {

    }
}
