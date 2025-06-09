package com.summer.lib.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ToastUtils
import com.google.common.reflect.Reflection.getPackageName


object PermissionUtil {

    const val reqcode: Int = 12345



    fun requestPermissions(context: Activity, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                // 已有权限，可以访问所有文件
                return true
            } else {
                // 请求权限
                requestAllFilesAccessPermission(context)
                return false
            }
        }else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        if (checkPermissionAllGranted(context, permissions)) {
            return true
        }
        ActivityCompat.requestPermissions(context, permissions, reqcode)
        return false
    }

    private fun requestAllFilesAccessPermission(context: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.setData(Uri.parse("package:" + context.getPackageName()))
                context.startActivityForResult(intent, 1)
            } catch (e: Exception) {
                // 某些设备可能不支持，回退到应用详情页
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.setData(Uri.parse("package:" + context.getPackageName()))
                context.startActivity(intent)
            }
        }
    }

    fun onRequestPermissionsResult(
        activity: Activity,
        requestCode: Int,
        grantResults: IntArray
    ): Boolean {
        if (requestCode == reqcode) {
            var isallgranted = true
            for (grnat in grantResults) {
                if (grnat != PackageManager.PERMISSION_GRANTED) {
                    isallgranted = false
                    break
                }
            }
            if (isallgranted) {
                return true
            } else {
                ToastUtils.showShort("请到应用权限管理中找到此应用并手动打开权限")
                val intent = Intent(Settings.ACTION_SETTINGS)
                activity.startActivity(intent)
                activity.finish()
            }
        }
        return false
    }

    private fun checkPermissionAllGranted(context: Context, permissions: Array<String>): Boolean {
        for (s in permissions) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    s
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

}