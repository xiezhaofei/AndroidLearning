package com.android.androidlearning.learningcode.viewpager;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.androidlearning.R;
import com.android.androidlearning.learningcode.annotation.TestAnnotationFragment;
import com.android.androidlearning.learningcode.fragment.BaseFragment;
import com.android.androidlearning.learningcode.okhttp.TestOKhttpFragment;
import com.android.androidlearning.learningcode.proxy.TestDynamicProxyFragment;

import java.util.ArrayList;
import java.util.List;

public class TestViewPagerFragment extends BaseFragment {
    private ViewPager mViewPager;
    private SmartTabLayout mIndicator;

    @Override
    protected int getLayoutId() {
        return R.layout.fra_test_viewpager;
    }

    @Override
    protected void initViews() {
        mViewPager = findViewById(R.id.viewpager);
        mIndicator = findViewById(R.id.indicator);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TestAnnotationFragment());
        fragments.add(new TestDynamicProxyFragment());
        fragments.add(new TestOKhttpFragment());

        mViewPager.setAdapter(new XFragmentPagerAdapter(getChildFragmentManager(), fragments));
        mIndicator.setViewPager(mViewPager);

    }

    @Override
    protected void initValues(Bundle savedInstanceState) {
    }

    class XFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public XFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "你哈";
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
