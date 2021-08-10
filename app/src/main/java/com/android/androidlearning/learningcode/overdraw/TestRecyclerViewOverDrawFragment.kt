package com.android.androidlearning.learningcode.overdraw

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.androidlearning.R
import com.android.androidlearning.learningcode.fragment.BaseFragment

class TestRecyclerViewOverDrawFragment : BaseFragment() {

    private lateinit var rvOuter: RecyclerView

    private class TestRecycledViewPool : RecyclerView.RecycledViewPool() {
        override fun getRecycledView(viewType: Int): RecyclerView.ViewHolder? {
            val result = super.getRecycledView(viewType)
            Log.d("xzf", "getRecycledView")
//            result?.itemView?.let {
//                (it.parent as? ViewGroup)?.removeView(it)
//            }
            return result
        }
    }

    private val pool: RecyclerView.RecycledViewPool by lazy {
        TestRecycledViewPool().apply {
//            for (count in 0..10) {
//                putRecycledView(OutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_test_over_draw, null), pool))
//            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fra_recycler_view_over_draw
    }

    override fun initViews() {
        rvOuter = findViewById(R.id.rv_outer)
        var start = System.currentTimeMillis()
        rvOuter.layoutManager = LinearLayoutManager(context)
        rvOuter.adapter = OutAdapter(pool)
        Log.d("xzf", "create : ${(System.currentTimeMillis() - start)}")
    }

    override fun initValues(savedInstanceState: Bundle?) {
    }

}

private class OutViewHolder(view: View, pool: RecyclerView.RecycledViewPool) : RecyclerView.ViewHolder(view) {
    private val listview = view.findViewById<RecyclerView>(R.id.rv_item)
    private val datas = ArrayList<String>()
    private val adapter: InnerAdapter = InnerAdapter(datas)

    init {
        listview.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        listview.adapter = adapter
        (listview.layoutManager as LinearLayoutManager).isItemPrefetchEnabled = true
        listview.setRecycledViewPool(pool)
    }

    fun bindData(list: List<String>) {
        val start = System.currentTimeMillis()
        datas.clear()
        datas.addAll(list)
        adapter.notifyDataSetChanged()
        Log.d("xzf", "bindData:${(System.currentTimeMillis() - start)}")
    }
}

private class OutAdapter(private val pool: RecyclerView.RecycledViewPool) : RecyclerView.Adapter<OutViewHolder>() {

    private val datas: ArrayList<List<String>> = ArrayList()

    init {
        for (value in 0 until 50) {
            val tmp = ArrayList<String>()
            for (value2 in 0 until 15) {
                tmp.add("$value2")
            }
            datas.add(tmp)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutViewHolder {
        Log.d("xzf", "onCreateViewHolder.....")
        return OutViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_test_over_draw, parent, false), pool)
    }

    override fun onBindViewHolder(holder: OutViewHolder, position: Int) {
        holder.bindData(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class InnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image = itemView.findViewById<ImageView>(R.id.image_view)
    fun bindData() {
        image.setImageResource(R.drawable.test)
    }
}

class InnerAdapter constructor(var list: List<String>) : RecyclerView.Adapter<InnerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        return InnerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_test_over_draw_img, parent, false))
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
