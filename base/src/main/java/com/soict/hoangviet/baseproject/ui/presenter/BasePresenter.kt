package com.soict.hoangviet.baseproject.ui.presenter

import com.soict.hoangviet.baseproject.ui.interactor.BaseInteractor
import com.soict.hoangviet.baseproject.ui.view.BaseView

interface BasePresenter<V : BaseView, I : BaseInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?
}