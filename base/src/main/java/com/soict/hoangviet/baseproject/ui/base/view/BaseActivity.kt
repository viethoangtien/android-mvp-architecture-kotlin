package com.soict.hoangviet.baseproject.ui.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soict.hoangviet.baseproject.common.BaseLoadingDialog
import com.soict.hoangviet.baseproject.ui.base.presenter.BasePresenter

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseView, BaseFragment.CallBack {
    protected abstract val mLayoutRes: Int
    protected val mPresenter get() = getPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayoutRes)
        mPresenter?.onAttach()
    }

    override fun showLoading() {
        BaseLoadingDialog.getInstance(this).showLoadingDialog()
    }

    override fun hideLoading() {
        BaseLoadingDialog.getInstance(this).hideLoadingDialog()
    }

    abstract fun getPresenter(): P
}