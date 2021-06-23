package com.android.androidlearning.learningcode.layer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi

class LayerMirrorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private val mDatas = ArrayList<Rect>()

    private val backgroundColorList = listOf(Color.GRAY, Color.GREEN, Color.RED, Color.LTGRAY)

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun setData(datas: List<Rect>, vWidth: Int, vHeight: Int) {
        if (!isChanged(datas, mDatas)) {
            return
        }
        removeAllViews()
        mDatas.clear()
        mDatas.addAll(datas)
        mDatas.forEachIndexed { index, rect ->
            val textview = TextView(context)
            val layoutParams: FrameLayout.LayoutParams = LayoutParams((rect.right - rect.left) * width / vWidth, (rect.bottom - rect.top) * height / vHeight)
            textview.gravity = Gravity.CENTER
            textview.text = "${index + 1}"
            textview.setBackgroundColor(backgroundColorList[index % backgroundColorList.size])
            layoutParams.setMargins(rect.left * width / vWidth, rect.top * height / vHeight, 0, 0)
            Log.d("xzf","(${rect.left * width / vWidth},${rect.top * height / vHeight})")
            addView(textview, layoutParams)
        }
        requestLayout()
    }

    private fun isChanged(old: List<Rect>, new: List<Rect>): Boolean {
        if (old.size != new.size) {
            return true
        }
        old.forEachIndexed { index, rect ->
            if (new[index] != rect) {
                return true
            }
        }
        return false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidth = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            100
        } else {
            MeasureSpec.getSize(widthMeasureSpec)
        }
        val measureHeight = if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            100
        } else {
            MeasureSpec.getSize(heightMeasureSpec)
        }
        setMeasuredDimension(measureWidth, measureHeight)

    }
}