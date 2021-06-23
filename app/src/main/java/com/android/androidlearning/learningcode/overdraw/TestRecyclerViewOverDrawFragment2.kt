package com.android.androidlearning.learningcode.overdraw

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.androidlearning.R
import com.android.androidlearning.learningcode.fragment.BaseFragment

class TestRecyclerViewOverDrawFragment2 : BaseFragment() {

    private lateinit var recyclerView: RecyclerView

    override fun getLayoutId(): Int {
        return R.layout.fra_recycler_view_over_draw
    }

    override fun initViews() {
        recyclerView = findViewById(R.id.rv_outer)
        recyclerView.layoutManager = GridLayoutManager(context, 10)
        val datas = ArrayList<String>()
        for (index in 0 until 300) {
            datas.add("$index")
        }
        recyclerView.adapter = InnerAdapter(datas)

    }

    override fun initValues(savedInstanceState: Bundle?) {

    }
}


