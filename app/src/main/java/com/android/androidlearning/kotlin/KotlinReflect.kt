package com.android.androidlearning.kotlin

object KotlinReflect {
    fun reflect() {
        val testTool = TestTool()
        val testType = TestTool::class.java
        val listField = testType.getDeclaredField("testlist")
        listField.isAccessible = true
        val listObject = listField.get(testTool)
        val m = listObject.javaClass.getDeclaredMethod("add",Any::class.java)
        m.invoke(listObject,"fuck")

//        val list = ArrayList<String>().apply {
//            add("hello")
//            add("world")
//        }
//
//        listField.isAccessible = true
//
//        listField.set(testTool, list)



        testTool.out()

    }
}