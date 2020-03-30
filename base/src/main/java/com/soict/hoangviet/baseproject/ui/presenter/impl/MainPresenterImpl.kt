package com.soict.hoangviet.baseproject.ui.presenter.impl

import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import com.soict.hoangviet.baseproject.ui.interactor.MainInteractor
import com.soict.hoangviet.baseproject.ui.presenter.MainPresenter
import com.soict.hoangviet.baseproject.ui.view.MainView
import com.soict.hoangviet.baseproject.utils.ToastUtil
import javax.inject.Inject

class MainPresenterImpl @Inject internal constructor(
    mInteractor: MainInteractor,
    sharePreference: SharePreference
) :
    BasePresenterImpl<MainView, MainInteractor>(
        mInterator = mInteractor,
        mAppSharePreference = sharePreference
    ),
    MainPresenter {
    private var page = 1
    private var totalPage = 0
    override fun fetchListDriver() {
        val data: MutableMap<String, Any> = mutableMapOf(
            "status" to "",
            "empty_seat" to "",
            "start_point" to "",
            "start_time" to "",
            "end_point" to "",
            "limit" to 6,
            "page" to page
        )
        (mInterator as MainInteractor)?.let {
            it.fetchListDriver(data)
                .subscribe({
                    ToastUtil.show("Good Job!")
                }, {
                    handleThrowable(it)
                })
        }
    }
}