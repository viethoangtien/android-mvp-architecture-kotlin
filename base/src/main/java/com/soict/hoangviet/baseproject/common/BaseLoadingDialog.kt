package com.soict.hoangviet.baseproject.common

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import com.soict.hoangviet.baseproject.R

class BaseLoadingDialog private constructor(var context: Context) {

    private var mDialog: Dialog? = null
    private var shown: Boolean? = null

    //Before initialize instance of class
    //Before class constructor
    companion object {
        private lateinit var instance: BaseLoadingDialog
        fun getInstance(context: Context): BaseLoadingDialog {
            if (instance == null) {
                instance = BaseLoadingDialog(context)
            }
            return instance
        }
    }

    //After primary constructor
    init {
        mDialog = Dialog(context)
        mDialog?.apply {
            setContentView(R.layout.layout_loading)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        shown = false
    }

    private fun showLoadingDialog() {
        if (!shown!! && !(context as Activity).isFinishing) {
            force()
            mDialog?.show()
        }
    }

    private fun hideLoadingDialog() {
        if (shown!! && (mDialog?.isShowing!!)) {
            initialization()
            mDialog?.dismiss()
        }
    }

    private fun force() {
        shown = true
    }

    private fun initialization() {
        shown = false
    }
}