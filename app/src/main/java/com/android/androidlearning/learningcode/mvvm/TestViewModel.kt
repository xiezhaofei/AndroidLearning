package com.android.androidlearning.learningcode.mvvm

import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel() {

    private fun test(): Int {
        return 1
    }

    private fun test2(): Int = test()

}


