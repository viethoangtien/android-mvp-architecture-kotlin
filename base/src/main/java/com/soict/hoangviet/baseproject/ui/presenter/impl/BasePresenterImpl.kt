package com.soict.hoangviet.baseproject.ui.presenter.impl

import com.soict.hoangviet.baseproject.ui.interactor.BaseInterator
import com.soict.hoangviet.baseproject.ui.presenter.BasePresenter
import com.soict.hoangviet.baseproject.ui.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BasePresenterImpl<V : BaseView, I : BaseInterator>
internal constructor(
    protected var mInterator: I?,
    protected val mCompositeDisposable: CompositeDisposable
) : BasePresenter<V, I> {
    private var mView: V? = null
    protected val isAttached get() = mView != null

    override fun onAttach(view: V?) {
        mView = view
    }

    override fun getView(): V? {
        return mView
    }


    override fun onDetach() {
        mView = null
        mInterator = null
        mCompositeDisposable.clear()
    }
}