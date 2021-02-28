package com.android.androidlearning.kotlin

import java.lang.StringBuilder

fun main(args: Array<String>) {
    val m = Main()
    val str = m.method.invoke()
    println(str)

    fun Main.method2(str: String): String {
        return "test $str"
    }

    println(m.method2("class的拓展方法"))

    m.method7(1) { x: Int, y: Int -> x + y }

    val num = m.method7(1) { x: Int, y: Int ->
        x + y
    }

}


class Main {
    private lateinit var string: String

    private val string2: String by lazy {
        val sb: StringBuilder = StringBuilder()
        sb.append("test")
        sb.toString()
    }

    val method: () -> String = {
        string = string2
        println("method")
        string
    }

    val method2: (Int) -> Int = { it + 1 }
    val method3 = { 1 + 2 + 3 }
    val method4: (Int, Int) -> Int = { i: Int, i2: Int -> i + i2 }
    val method5: () -> Unit = {}
    val method6 = {}

    fun method7(a: Int, b: (x: Int, y: Int) -> Int): Int {
        return a + b.invoke(1, 2)
    }

    val method8 = fun(a: Int): Int = a
    val method9 = fun(a: Int, b: Int): Int = a + b
    val method10 = fun(a: Int, b: Int) = a + b
    val method11 = fun(a: Int, b: Int) {
        a + b
    }


}

