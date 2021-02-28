package com.android.androidlearning.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.androidlearning.TestSheetActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetView : RelativeLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyleRes: Int) : super(context, attr, defStyleRes)

    init {
        context?.let {
            initUi(it)
        }
    }


    private fun initUi(context: Context) {


        val coordinatorLayout = CoordinatorLayout(context)
        val layoutParams = CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.BOTTOM
        addView(coordinatorLayout, layoutParams)

        val recyclerView = RecyclerView(context)
        val rvLayout = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        coordinatorLayout.addView(recyclerView, rvLayout)
        (recyclerView.layoutParams as CoordinatorLayout.LayoutParams).behavior = BottomSheetBehavior<RecyclerView>()

        val bottomSheetBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(recyclerView)
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.peekHeight = 500
        bottomSheetBehavior.isHideable = false

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TestSheetActivity.MyAdapter()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view as TextView
    }

    class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        private val list = listOf("item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item", "item")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val textView: TextView = TextView(parent.context)
            return MyViewHolder(textView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = "${list[position]}${position}"
        }

        override fun getItemCount(): Int {
            return list.size
        }

    }

}