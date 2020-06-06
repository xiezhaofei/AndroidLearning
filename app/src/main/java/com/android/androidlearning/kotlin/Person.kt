package com.android.androidlearning.kotlin

open class Person() {
    private var name: String = "";
    val location: String = "";
    var age: Int = 1;

    companion object {}


    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }

    fun getName(): String {
        return name;
    }


    open fun sayHello() {
        println("person say hello")
    }

    open fun executeRuns(runnable: () -> Unit) {
        runnable.invoke();
    }
}