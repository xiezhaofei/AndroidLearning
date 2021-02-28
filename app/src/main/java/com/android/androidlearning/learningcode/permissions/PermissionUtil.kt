package com.android.androidlearning.learningcode.permissions

import android.Manifest
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

object PermissionUtil {
    fun isGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * 判断某个权限集合是否包含特殊权限
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun containsSpecialPermission(permissions: List<String?>?): Boolean {
        if (permissions == null || permissions.isEmpty()) {
            return false
        }
        for (permission in permissions) {
            if (isSpecialPermission(permission)) {
                return true
            }
        }
        return false
    }

    /**
     * 判断某个权限是否是特殊权限
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun isSpecialPermission(permission: String?): Boolean {
        return Manifest.permission.REQUEST_INSTALL_PACKAGES == permission ||
                Manifest.permission.SYSTEM_ALERT_WINDOW == permission ||
                Manifest.permission.ACCESS_NOTIFICATION_POLICY == permission ||
                Manifest.permission.WRITE_SETTINGS == permission
    }
}