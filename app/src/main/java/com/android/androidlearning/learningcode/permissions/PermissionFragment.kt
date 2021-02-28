package com.android.androidlearning.learningcode.permissions

import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import java.lang.StringBuilder
import java.util.ArrayList

class PermissionFragment : Fragment() {
    private var mPermissions: Array<String>? = null
    private var requestCode: Int = -1
    private var callback: PermissionsRequesterImpl.OnPermissionCallback? = null

    companion object {
        const val TAG = "PermissionFragment"
        const val REQUEST_CODE = "requestCode"
        const val PERMISSIONS = "permissions"
        fun newInstance(permissions: Array<String>, requestCode: Int): PermissionFragment {
            val fragment = PermissionFragment()
            val bundle = Bundle()
            bundle.putInt(REQUEST_CODE, requestCode)
            bundle.putStringArray(PERMISSIONS, permissions)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPermissions = it.getStringArray(PERMISSIONS)
            requestCode = it.getInt(REQUEST_CODE)
        }

        mPermissions?.let {
            requestPermissions(it, requestCode)
        }
    }



    override fun onResume() {
        super.onResume()
    }

    fun setPermissionCallback(callback: PermissionsRequesterImpl.OnPermissionCallback) {
        this.callback = callback
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val stringBuffer = StringBuilder()
        for (i in (permissions.indices)) {
            stringBuffer.append(permissions[i]).append(" is granted ").append(grantResults[i] == 0)
        }
        Log.d(TAG, "onRequestPermissionsResult $stringBuffer")
        callback?.let {
            val grantedList = ArrayList<String>()
            val deniedList = ArrayList<String>()
            for (i in permissions.indices) {
                if (grantResults[i] == PERMISSION_GRANTED) {
                    grantedList.add(permissions[i])
                } else {
                    deniedList.add(permissions[i])
                }
            }
            it.onGranted(grantedList)
            it.onDenied(deniedList)
        }
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commitNowAllowingStateLoss()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}