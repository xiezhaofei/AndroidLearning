package com.android.androidlearning.learningcode.layer

import android.view.View
import java.util.*
import kotlin.collections.ArrayList

object ReDrawDetectHelper {

    fun getReDrawViewNode() {

    }

    fun test_getInVisibleViewNode(nodes: List<RenderNode>) {
        val result = getInVisibleRenderNode(nodes)
        result?.run {
            for (value in this) {
                value.viewNode.targetView.visibility = View.GONE
                println("xzf " + value.string())
            }
        }
    }


    fun test_getHidenRenderNode(nodes: List<RenderNode>) {
        val result = getRedundantViewNodeForTarget(nodes)
        result.run {
            for (value in this) {
                value.viewNode.targetView.visibility = View.GONE
                println("xzf " + value.string())
            }
        }
    }

    private fun getInVisibleRenderNode(nodes: List<RenderNode>): List<RenderNode>? {
        if (nodes.isEmpty()) {
            return null
        }

        val result = ArrayList<RenderNode>()
        var tmp = nodes

        while (true) {
            val wrapper = getInVisibleViewNodeForTarget(tmp, tmp.last())
            if (wrapper.nodes2.isNotEmpty()) {
                result.addAll(wrapper.nodes2)
            }
            if (wrapper.nodes1.isNotEmpty()) {
                tmp = wrapper.nodes1
            } else {
                break
            }
        }
        return result
    }

    private fun getInVisibleViewNodeForTarget(nodes: List<RenderNode>, target: RenderNode): ViewNodeListWrapper {
        val nodes1 = LinkedList<RenderNode>()
        val nodes2 = LinkedList<RenderNode>()
        for (value in nodes.reversed()) {
            if (value == target) {
                continue
            }
            if (target.viewNode.contains(value.viewNode)) {
                nodes2.add(value)
            } else {
                nodes1.add(value)
            }
        }
        return ViewNodeListWrapper(nodes1.reversed(), nodes2)
    }

    /**
     * 背景什么情况可以去掉？
     * A覆盖在B上面，而且A B的背景相同，这是可以去掉A的背景。
     */
    private fun getRedundantViewNodeForTarget(nodes: List<RenderNode>): List<RenderNode> {
        val nodes1 = LinkedList<RenderNode>()
        val nodesreversed = nodes.reversed()
        for ((index, value) in nodesreversed.withIndex()) {
            var x = index + 1
            var result = false
            while (x < nodes.size) {
                val n = nodesreversed[x]
                if (n.viewNode.overlap(value.viewNode)) {
                    if (backgroundIsTheSame(n, value)) {
                        continue
                    }
                } else if (n.viewNode.contains(value.viewNode)) {
                    if (backgroundIsTheSame(n, value)) {
                        result = true
                        break
                    }
                }
                x++
            }
            if (result) {
                nodes1.add(value)
            }
        }
        return nodes1
    }

    private fun backgroundIsTheSame(a: RenderNode, b: RenderNode): Boolean {
        return a.viewNode.getBackgroundColor() != null && b.viewNode.getBackgroundColor() != null && b.viewNode.getBackgroundColor() == a.viewNode.getBackgroundColor()
    }

    data class ViewNodeListWrapper(var nodes1: List<RenderNode>, var nodes2: List<RenderNode>)
}