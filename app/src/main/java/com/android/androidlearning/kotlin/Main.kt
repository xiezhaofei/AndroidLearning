package com.android.androidlearning.kotlin

import java.lang.StringBuilder

fun main(args: Array<String>) {
    val m = Main()
//    val str = m.method.invoke()
//    println(str)
//
//    fun Main.method2(str: String): String {
//        return "test $str"
//    }
//
//    println(m.method2("class的拓展方法"))
//
//    m.method7(1) { x: Int, y: Int -> x + y }
//
//    m.extendFun()
//
//    val num = m.method7(1) { x: Int, y: Int ->
//        x + y
//    }
//
//    val test = "testcode".also {
//        println("lllllll")
//    }
//    println(test)
//
//    for (index in 0 until 0) {
//        println("index $index")
//    }

//    val obj = MObject()
//    obj.getName().printName()
//    obj.getName().printName()
//    obj.getName().printName()

    m.method12 {
        println(it)
    }

    m.method13(2, 3)

    m.method13.invoke(2, 3)


}

fun Main.extendFun() {

    "".let {

    }
    println("entend fun")
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
    val method5: () -> Unit = {
    }
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

    fun method12(cansu: (Int) -> Unit) {
        cansu(2021)
        cansu.invoke(2021)
    }

    val method13 = { i: Int, i2: Int -> i + i2 }




    //函数参数，谁调用，谁传参。
}

class MObject {
    var name = "xzf"
    var count: Int = 0

    fun test() = run {
        this.apply {
            name = "hello ${count++}"
        }
    }

    fun getName() = this.apply {
        name = "hello ${count++}"
    }

    fun printName() = run { println(name) }
}
