package com.android.androidlearning.kotlin

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.androidlearning.BaseActivity
import com.android.androidlearning.BaseActivity2
import com.android.androidlearning.utils.ALLog
import java.sql.DriverManager.println
import com.android.androidlearning.R
import com.bumptech.glide.Glide

/**
 * Created by xiezhaofei on 2020-03-27
 *
 * Describe:
 */
class KotlinActivity : BaseActivity() {
//    val TAG: String = "KotlinActivity"

    init {
        ALLog.d(TAG, "init...");
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_kotlin_learn)


        fun Person.Companion.foo() {
            ALLog.d(TAG, "FOO")
        }
        
        ALLog.d(TAG, Person.Companion.foo())
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}