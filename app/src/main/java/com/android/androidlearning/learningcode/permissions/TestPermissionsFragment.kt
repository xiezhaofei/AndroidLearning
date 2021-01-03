package com.android.androidlearning.learningcode.permissions

import android.Manifest
import android.util.Log
import com.android.androidlearning.learningcode.fragment.BaseFragment2

class TestPermissionsFragment : BaseFragment2() {
    override fun initViews() {
        super.initViews()
        addButton("请求camera权限") {
            activity?.let {
                var array: Array<String> = arrayOf(Manifest.permission.CAMERA, Manifest.permission.SYSTEM_ALERT_WINDOW)
                PermissionsRequesterImpl.with(it).permission(array).request(object : PermissionsRequesterImpl.OnPermissionCallback {
                    override fun onGranted(permissions: List<String>) {
                        Log.d("TestPermissionsFragment", "onGranted")
                    }

                    override fun onDenied(permissions: List<String>) {
                        Log.d("TestPermissionsFragment", "onDenied")
                    }

                })
            }

        }
    }
}