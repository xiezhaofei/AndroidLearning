package com.android.androidlearning.learningcode.layer

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.android.androidlearning.R
import com.android.androidlearning.learningcode.fragment.BaseFragment

class TestLayerDetailFragment : BaseFragment() {

    private val viewContainer: FrameLayout by lazy {
        findViewById<FrameLayout>(R.id.fl_view_container)
    }

    private val layerMirrorView: LayerMirrorView by lazy {
        findViewById<LayerMirrorView>(R.id.layer)
    }

    override fun getLayoutId(): Int {
        return R.layout.fra_test_layer_detail
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun initViews() {
        findViewById<View>(R.id.btn_test_parser).setOnClickListener {
            val list = ViewTreeParser.parserAllBackground(viewContainer)
            val visibleRect = Rect().also { viewContainer.getGlobalVisibleRect(it) }
            val rectList = ArrayList<Rect>()
            list.forEach {
                rectList.add(it.viewNode.rect.apply {
                    left -= visibleRect.left
                    top -= visibleRect.top
                    right -= visibleRect.left
                    bottom -= visibleRect.top
                })
            }
            layerMirrorView.setData(rectList, viewContainer.width, viewContainer.height)
        }
        findViewById<View>(R.id.btn_test_get_invisible_view).setOnClickListener {
            ReDrawDetectHelper.test_getInVisibleViewNode(ViewTreeParser.parserAllBackground(viewContainer))
        }
        findViewById<View>(R.id.btn_test_get_redundant_view).setOnClickListener {
            ReDrawDetectHelper.test_getHidenRenderNode(ViewTreeParser.parserAllBackground(viewContainer))
        }

    }

    override fun initValues(savedInstanceState: Bundle?) {
    }
}