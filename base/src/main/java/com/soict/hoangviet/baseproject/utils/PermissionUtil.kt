package com.soict.hoangviet.baseproject.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.soict.hoangviet.baseproject.application.BaseApplication

object PermissionUtil {
    /**
     * Open setting permission from activity
     */
    fun openSettingPermission(activity: Activity, requestCode: Int) {
        val intentSetting = Intent()
        intentSetting.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", BaseApplication.instance.packageName, null)
        intentSetting.setData(uri)
        activity.startActivityForResult(intentSetting, requestCode)
    }

    /**
     * Open setting permission from fragment
     */
    fun openSettingPermission(fragment: Fragment, requestCode: Int) {
        val intentSetting = Intent()
        intentSetting.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", BaseApplication.instance.packageName, null)
        intentSetting.setData(uri)
        fragment.startActivityForResult(intentSetting, requestCode)
    }

    fun hasPermission(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(
            BaseApplication.instance,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Check permission from activity
     */
    fun requestPermission(activity: Activity, permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    }

    /**
     * Check permission from fragment
     */
    fun requestPermission(fragment: Fragment, permission: String, requestCode: Int) {
        fragment.requestPermissions(arrayOf(permission), requestCode)
    }

}