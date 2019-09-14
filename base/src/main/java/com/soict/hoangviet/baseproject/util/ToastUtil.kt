package com.soict.hoangviet.baseproject.util

import android.widget.Toast
import androidx.annotation.StringRes
import com.soict.hoangviet.baseproject.application.BaseApplication

object ToastUtil {
    fun show(string: String) {
        Toast.makeText(BaseApplication.instance, string, Toast.LENGTH_SHORT).show()
    }

    fun show(@StringRes stringRes: Int) {
        Toast.makeText(BaseApplication.instance, stringRes, Toast.LENGTH_SHORT).show()
    }

}