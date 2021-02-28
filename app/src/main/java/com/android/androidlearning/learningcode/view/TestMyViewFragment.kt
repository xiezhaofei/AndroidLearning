package com.android.androidlearning.learningcode.view

import android.os.Bundle
import android.view.View
import com.android.androidlearning.R
import com.android.androidlearning.learningcode.fragment.BaseFragment

class TestMyViewFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.common_description
    }

    override fun initViews() {
    }

    override fun initValues(savedInstanceState: Bundle?) {
    }
}