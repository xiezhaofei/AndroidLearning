package com.android.androidlearning.learningcode.layer

data class RenderNode(val viewNode: ViewNode, val renderType: RenderType)

enum class RenderType {
    ViewContent,
    ViewBackground
}

fun RenderNode.string(): String {
    return "renderType=$renderType,viewNode=${viewNode.string()}"
}