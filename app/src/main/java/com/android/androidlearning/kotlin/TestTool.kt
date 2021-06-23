package com.android.androidlearning.kotlin

/**
 * Created by xiezhaofei on 2020-03-27
 *
 * Describe:
 */
class TestTool {

    private val testlist = ArrayList<String>()

    fun out() {
        println("xzf ${testlist.size}")
        if (testlist.isNotEmpty()) {
            testlist.forEach {
                println("xzf $it")
            }
        }
    }

}