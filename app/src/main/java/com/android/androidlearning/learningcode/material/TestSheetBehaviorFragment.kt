package com.android.androidlearning.learningcode.material

import android.os.Bundle
import android.view.View
import com.android.androidlearning.R
import com.android.androidlearning.learningcode.fragment.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class TestSheetBehaviorFragment : BaseFragment() {
    private val root: View by lazy {
        findViewById<View>(R.id.root)
    }

    override fun getLayoutId(): Int {
        return R.layout.fra_sheet_behavior
    }

    override fun onResume() {
        super.onResume()
        val behavior = BottomSheetBehavior.from(root)
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(p0: View, p1: Int) {
            }

            override fun onSlide(p0: View, p1: Float) {
            }

        })
    }

    override fun initViews() {
    }

    override fun initValues(savedInstanceState: Bundle?) {
    }
}