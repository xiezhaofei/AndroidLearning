package com.android.androidlearning.learningcode.permissions

import android.os.Build
import android.util.Log
import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference
import java.util.*

class PermissionsRequesterImpl {
    private var activityWeakReference: WeakReference<FragmentActivity>? = null
    private val mPermissions: MutableList<String> = LinkedList()

    companion object {
        const val TAG = "PowerPermissions"
        fun with(activity: FragmentActivity): PermissionsRequesterImpl {
            val requester = PermissionsRequesterImpl()
            requester.activityWeakReference = WeakReference(activity)
            return requester
        }
    }


    fun permission(permissions: Array<String>): PermissionsRequesterImpl {
        mPermissions.addAll(permissions)
        return this
    }

    fun permission(permission: String): PermissionsRequesterImpl {
        mPermissions.add(permission)
        return this
    }


    fun request(callback: OnPermissionCallback) {
        activityWeakReference?.let {
            val activity = it.get()
            if (activity == null || activity.isFinishing
                    || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed)
                    || mPermissions.isEmpty()
            ) {
                //异常情况，返回
                Log.d(TAG, "return directly")
                return
            } else {
                var allGranted = true
                for (i in mPermissions.indices) {
                    if (!PermissionUtil.isGranted(activity, mPermissions[i])) {
                        allGranted = false
                        break
                    }
                }
                if (allGranted) {
                    Log.d(TAG, "all granted")
                    callback.onGranted(mPermissions)
                    callback.onDenied(listOf())
                } else {
                    val fragment = PermissionFragment.newInstance(mPermissions.toTypedArray(), 100)
                    fragment.setPermissionCallback(callback)
                    activity.supportFragmentManager.beginTransaction().add(fragment, "PermissionFragment").commitNowAllowingStateLoss()
                    Log.d(TAG, "try to request permissions")
                }
            }

        } ?: let {
            Log.d(TAG, "activity is null")
        }
    }

    interface OnPermissionCallback {
        fun onGranted(
                permissions: List<String>
        )

        fun onDenied(
                permissions: List<String>
        )
    }
}