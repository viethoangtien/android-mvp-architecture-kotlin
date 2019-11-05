package com.soict.hoangviet.baseproject.ui.base.presenter

import com.soict.hoangviet.baseproject.ui.base.interactor.BaseInterator
import com.soict.hoangviet.baseproject.ui.base.view.BaseView
import io.reactivex.disposables.CompositeDisposable

class BasePresenterImpl<V : BaseView, I : BaseInterator>(mView: V, mInteractor: I) : BasePresenter {
    protected var mView: V? = mView
    protected var mInterator: I? = mInteractor
    protected var mCompositeDisposable = CompositeDisposable()
    protected val isAttached get() = mView != null

    override fun onAttach() {
        mView?.let {
            it.initView()
        }
    }

    override fun onDetach() {
        mView = null
        mInterator = null
        mCompositeDisposable.clear()
    }
}