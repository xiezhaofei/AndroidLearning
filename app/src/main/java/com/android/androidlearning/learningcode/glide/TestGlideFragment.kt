package com.android.androidlearning.learningcode.glide

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.android.androidlearning.R
import com.android.androidlearning.learningcode.fragment.BaseFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fra_test_glide.*

/**
 * Created by xiezhaofei on 2020/4/23
 *
 *
 * Describe:
 */
class TestGlideFragment : BaseFragment() {
    private var imageView: ImageView? = null
    override fun getLayoutId(): Int {
        return R.layout.fra_test_glide
    }

    override fun initViews() {
        imageView = findViewById(R.id.image)


        findViewById<View>(R.id.btn_1).setOnClickListener {
            Glide.with(context)
                    .load("https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg")
                    .into(imageView)
        }
    }

    override fun initValues(savedInstanceState: Bundle) {}
}