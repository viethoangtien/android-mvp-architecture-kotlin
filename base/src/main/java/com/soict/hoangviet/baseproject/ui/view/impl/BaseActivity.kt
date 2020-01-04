package com.soict.hoangviet.baseproject.ui.view.impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soict.hoangviet.baseproject.common.BaseLoadingDialog
import com.soict.hoangviet.baseproject.ui.presenter.BasePresenter
import com.soict.hoangviet.baseproject.ui.view.BaseView
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), BaseView,
    BaseFragment.CallBack {
    protected abstract val mLayoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayoutRes)
        performDI()
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
}