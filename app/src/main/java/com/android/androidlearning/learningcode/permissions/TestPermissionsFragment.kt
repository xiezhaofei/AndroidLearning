package com.android.androidlearning.learningcode.permissions

import android.Manifest
import android.util.Log
import com.android.androidlearning.learningcode.fragment.BaseFragment2

class TestPermissionsFragment : BaseFragment2() {
    override fun initViews() {
        super.initViews()
        addButton("请求camera权限") {
            activity?.let {
                PermissionsRequesterImpl.with(it).permission(Manifest.permission.CAMERA).request(object : PermissionsRequesterImpl.OnPermissionCallback {
                    override fun onGranted(permissions: List<String>, all: Boolean) {
                        Log.d("TestPermissionsFragment", "onGranted all:$all")
                    }

                    override fun onDenied(permissions: List<String>, never: Boolean) {
                        Log.d("TestPermissionsFragment", "onDenied never:$never")
                    }

                })
            }

        }
    }
}