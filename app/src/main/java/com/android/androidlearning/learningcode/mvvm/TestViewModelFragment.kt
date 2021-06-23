package com.android.androidlearning.learningcode.mvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.android.androidlearning.learningcode.fragment.BaseFragment2


class TestViewModelFragment : BaseFragment2() {

    override fun initViews() {
        super.initViews()
        val factory = ViewModelProvider.AndroidViewModelFactory(context?.applicationContext as Application)
        val viewModel = ViewModelProvider(this.context as ViewModelStoreOwner, factory).get(TestViewModel::class.java)
        addButton("test") {
            viewModel.getUsers().observe(this@TestViewModelFragment, Observer<List<String>> {
                for (value in it) {
                    Log.d("xzf", "value=$value")
                }
            })
        }
    }
}