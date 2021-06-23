package com.android.androidlearning.learningcode.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel() {

    private val users: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<String>> {
        return users
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("xzf", "onCleared")
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
        Thread {
            val list = ArrayList<String>()
            list.add("test1")
            list.add("test2")
            list.add("test3")
            users.postValue(list)

            Thread.sleep(1000)
            list.add("test4")
            list.add("test5")
            list.add("test6")
            users.postValue(list)

            Thread.sleep(1000)
            list.add("test7")
            list.add("test8")
            list.add("test9")
            users.postValue(list)

        }.start()
    }

}


