package com.android.androidlearning

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.androidlearning.widget.BottomSheetView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import java.util.ArrayList


class TestSheetActivity : AppCompatActivity() {

    private val mRecyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recycler_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(BottomSheetView(this))
//        setContentView(R.layout.activity_bottom_sheet_content)
//        // get the bottom sheet view
//        // get the bottom sheet view
//        val llBottomSheet = findViewById<View>(R.id.recycler_view) as RecyclerView
//
//// init the bottom sheet behavior
//
//// init the bottom sheet behavior
//        val bottomSheetBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(llBottomSheet)
//
//// change the state of the bottom sheet
//
//// change the state of the bottom sheet
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
//
//// set the peek height
//
//// set the peek height
//        bottomSheetBehavior.peekHeight = 500
//
//// set hideable or not
//
//// set hideable or not
//        bottomSheetBehavior.isHideable = false
//
//// set callback for changes
//
//// set callback for changes
//        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {}
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
//        })
//        mRecyclerView.layoutManager = LinearLayoutManager(this)
//        mRecyclerView.adapter = MyAdapter()

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