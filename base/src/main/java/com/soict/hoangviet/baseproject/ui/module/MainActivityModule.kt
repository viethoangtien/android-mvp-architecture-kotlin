package com.soict.hoangviet.baseproject.ui.module

import com.soict.hoangviet.baseproject.ui.interactor.MainInteractor
import com.soict.hoangviet.baseproject.ui.interactor.impl.MainInteractorImpl
import com.soict.hoangviet.baseproject.ui.presenter.MainPresenter
import com.soict.hoangviet.baseproject.ui.presenter.impl.MainPresenterImpl
import com.soict.hoangviet.baseproject.ui.view.MainView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mMainInteractor: MainInteractorImpl)
            : MainInteractor = mMainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenterImpl: MainPresenterImpl<MainView, MainInteractor>)
            : MainPresenter<MainView, MainInteractor> = mainPresenterImpl
}