package com.soict.hoangviet.baseproject.ui.view.impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soict.hoangviet.baseproject.common.BaseLoadingDialog
import com.soict.hoangviet.baseproject.extension.toast
import com.soict.hoangviet.baseproject.ui.view.BaseView
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), BaseView {
    protected abstract val mLayoutRes: Int
    open val mOnAttachListener: (() -> Unit)? = null
    open val mOnDetachListener: ((String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayoutRes)
        performDI()
        initView()
        initListener()
    }

    private fun performDI() {
        AndroidInjection.inject(this)
    }

    override fun showLoading() {
        BaseLoadingDialog.getInstance(this).showLoadingDialog()
    }

    override fun hideLoading() {
        BaseLoadingDialog.getInstance(this).hideLoadingDialog()
    }

    override fun onError(message: String) {
        toast(message)
    }

    override fun onError(resId: Int) {
        toast(resId)
    }

    override fun onSuccess(message: String) {
        toast(message)
    }

    override fun onSuccess(resId: Int) {
        toast(resId)
    }

}