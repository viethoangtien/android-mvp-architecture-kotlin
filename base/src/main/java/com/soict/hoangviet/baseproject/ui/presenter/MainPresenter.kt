package com.soict.hoangviet.baseproject.ui.presenter

import com.soict.hoangviet.baseproject.ui.interactor.BaseInterator
import com.soict.hoangviet.baseproject.ui.view.BaseView

interface MainPresenter<V : BaseView, I : BaseInterator> : BasePresenter<V, I> {
    fun fetchListDriver()
}