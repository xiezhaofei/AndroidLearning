package com.android.androidlearning.learningcode.commonpool

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.android.androidlearning.R
import com.android.androidlearning.learningcode.fragment.BaseFragment

class TestRecyclerViewCommonPool : BaseFragment() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    override fun getLayoutId(): Int {
        return R.layout.fra_test_common_pool
    }

    override fun initViews() {
    }

    override fun initValues(savedInstanceState: Bundle?) {
    }
}