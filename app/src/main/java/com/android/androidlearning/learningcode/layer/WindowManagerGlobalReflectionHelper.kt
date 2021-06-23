package com.android.androidlearning.learningcode.layer

import android.annotation.SuppressLint
import android.view.View
import java.util.*

@SuppressLint("PrivateApi")
class WindowManagerGlobalReflectionHelper {
    private lateinit var views: ArrayList<View>

    init {
        try {
            val windowManagerGlobalClass = Class.forName("android.view.WindowManagerGlobal")
            val getInstanceMethod = windowManagerGlobalClass.getMethod("getInstance")
            val windowManagerGlobal = getInstanceMethod.invoke(null)
            val mViewsField = windowManagerGlobalClass.getDeclaredField("mViews")
            mViewsField.isAccessible = true
            views = mViewsField.get(windowManagerGlobal) as ArrayList<View>
        } catch (ignore: Exception) {
        }
    }

    fun getWindowTopView(): View? {
        return if (::views.isInitialized) {
            views.lastOrNull()
        } else {
            null
        }
    }
}