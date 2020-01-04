package com.soict.hoangviet.baseproject.ui.presenter

import com.soict.hoangviet.baseproject.ui.interactor.BaseInterator
import com.soict.hoangviet.baseproject.ui.view.BaseView

interface BasePresenter<V : BaseView, I : BaseInterator> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}