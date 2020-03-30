package com.soict.hoangviet.baseproject.ui.presenter

import com.soict.hoangviet.baseproject.ui.interactor.MainInteractor
import com.soict.hoangviet.baseproject.ui.view.MainView

interface MainPresenter : BasePresenter<MainView, MainInteractor> {
    fun fetchListDriver()
}