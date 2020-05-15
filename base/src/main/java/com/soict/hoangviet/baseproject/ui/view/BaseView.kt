package com.soict.hoangviet.baseproject.ui.view

import androidx.annotation.StringRes

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun initView()
    fun initListener()
    fun onError(@StringRes resId: Int)
    fun onError(message: String)
    fun onSuccess(@StringRes resId: Int)
    fun onSuccess(message: String)
}