package com.android.androidlearning.learningcode.layer

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewGroup

object ViewTreeParser {

    private val globalReflectionHelper = WindowManagerGlobalReflectionHelper()
    private const val TAG = "ViewTreeParser"

    fun visit() {
        val curDecorView = globalReflectionHelper.getWindowTopView()
        curDecorView?.run {
            print(parser(this))
        }
    }

    fun parser(view: View) {
        val result = ArrayList<ViewNode>()
    }

//    fun getRenderNodes(): List<RenderNode> {
//        val curDecorView = globalReflectionHelper.getWindowTopView()
//        val result = mutableListOf<RenderNode>()
//        if (curDecorView != null) {
//            traverseView(curDecorView, result)
//        }
//        return result
//    }

    fun parserChildren(view: View): List<ViewNode> {
        val result = ArrayList<ViewNode>()
        if (view is ViewGroup) {
            for (index in 0 until view.childCount) {
                view.getChildAt(index)?.run {
                    result.add(ViewNode(Rect().also { this.getGlobalVisibleRect(it) }, this))
                }
            }
        }
        return result
    }

    fun parserAllBackground(view: View): List<RenderNode> {
        val tmp = ArrayList<RenderNode>()
        traverseView(view, tmp)
        val result = ArrayList<RenderNode>()
        tmp.forEach {
            if (it.renderType == RenderType.ViewBackground) {
                result.add(it)
            }
        }
        return result
    }

    private fun traverseView(view: View, renderNodeInfoList: ArrayList<RenderNode>) {
        val visibleRect = Rect().also { view.getGlobalVisibleRect(it) }
        Log.d(TAG, visibleRect.toString())
        if (view.visibility != View.VISIBLE) return
        if (visibleRect.isEmpty) return
        if (view.background != null) {
            val viewNode = ViewNode(visibleRect, view)
            renderNodeInfoList.add(RenderNode(viewNode, RenderType.ViewBackground))
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                traverseView(
                        view.getChildAt(i),
                        renderNodeInfoList
                )
            }
        } else {
            renderNodeInfoList.add(RenderNode(ViewNode(visibleRect, view), RenderType.ViewContent))
        }
    }
}