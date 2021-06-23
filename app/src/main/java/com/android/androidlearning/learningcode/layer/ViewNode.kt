package com.android.androidlearning.learningcode.layer

import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup

data class ViewNode(
        val rect: Rect, // 在屏幕中的位置和大小
        val targetView: View
)

fun ViewNode.isViewGroup(): Boolean {
    return targetView is ViewGroup
}

fun ViewNode.hasBackground(): Boolean {
    return targetView.background != null
}

fun ViewNode.getBackgroundColor(): Int? {
    if (targetView.background is ColorDrawable) {
        return (targetView.background as ColorDrawable).color
    }
    return null
}

fun ViewNode.contains(node: ViewNode): Boolean {
    return (rect.contains(node.rect)) && node.targetView.visibility == View.VISIBLE && targetView.visibility == View.VISIBLE
}

fun ViewNode.overlap(node: ViewNode): Boolean {
    if (node.targetView.visibility == View.VISIBLE && targetView.visibility == View.VISIBLE) {
        node.rect.run {
            return !(bottom >= rect.top || top <= rect.bottom || left <= rect.right || right >= rect.left)
        }
    }
    return false
}

fun ViewNode.string(): String {
    return "[isViewGroup=${isViewGroup()},hasBackGround=${hasBackground()}," +
            "rect=[${rect.left},${rect.top},${rect.right},${rect.bottom}]," +
            "targetView=${targetView.toString()}]"
}

