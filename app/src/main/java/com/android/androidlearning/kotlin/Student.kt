package com.android.androidlearning.kotlin

class Student : Person() {
    override fun sayHello() {
        println("student say hello")
        val p: Person = Person();
        p.let {
            it.getName()
            it.location



        }


    }

    fun callExecuteRun() {
//        this.executeRuns(object : Runnable {
//            override fun run() {
//                TODO("Not yet implemented")
//            }
//        })
//        this.executeRuns {
//            fun run() {
//                TODO("Not yet implemented")
//            }
//        }
    }
}