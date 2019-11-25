package com.soict.hoangviet.baseproject.ui.interactor.impl

import com.soict.hoangviet.baseproject.data.network.ICallBack
import com.soict.hoangviet.baseproject.data.network.api.BaseRetrofit
import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import com.soict.hoangviet.baseproject.ui.interactor.MainInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainInteractorImpl : BaseInteratorImpl(), MainInteractor {
    override fun fetchListDriver(data: MutableMap<String, Any>, callBack: ICallBack<BaseListEntityResponse<TestResponse>>): Disposable {
        val subscriber = getSubscriber(callBack)
        return BaseRetrofit.getApiService().getListDriver(
                "",
                2,
                data
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber)
    }
}