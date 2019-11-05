package com.soict.hoangviet.baseproject.ui.base.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.soict.hoangviet.baseproject.common.BaseLoadingDialog
import com.soict.hoangviet.baseproject.ui.base.presenter.BasePresenter

abstract class BaseFragment<P : BasePresenter> : Fragment(), BaseView {
    private var parentActivity: AppCompatActivity? = null
    protected val mPresenter get() = getPresenter()

    override
    fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            parentActivity = context
        }
        (parentActivity as BaseActivity<*>)?.onFragmentAttached()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter?.let{
            it.onAttach()
        }
    }

    override fun showLoading() {
        parentActivity?.let {
            BaseLoadingDialog.getInstance(it).showLoadingDialog()
        }
    }

    override fun hideLoading() {
        parentActivity?.let {
            BaseLoadingDialog.getInstance(it).hideLoadingDialog()
        }
    }

    abstract fun getPresenter(): P

    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }
}