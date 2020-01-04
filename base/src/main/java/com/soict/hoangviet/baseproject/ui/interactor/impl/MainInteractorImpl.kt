package com.soict.hoangviet.baseproject.ui.interactor.impl

import com.soict.hoangviet.baseproject.data.network.ICallBack
import com.soict.hoangviet.baseproject.data.network.api.BaseRetrofit
import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import com.soict.hoangviet.baseproject.ui.interactor.MainInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainInteractorImpl
@Inject
internal constructor(mAppSharePreference: SharePreference) :
    BaseInteratorImpl(mAppSharePreference = mAppSharePreference),
    MainInteractor {
    override fun fetchListDriver(
        data: MutableMap<String, Any>,
        callBack: ICallBack<BaseListEntityResponse<TestResponse>>
    ): Disposable {
        val subscriber = getSubscriber(callBack)
        return BaseRetrofit.getApiService().getListDriver(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGktc3RhZ2luZy50aW1kYXR4ZS5jb21cL3YxXC91c2VyX3Nlc3Npb25zIiwiaWF0IjoxNTc4MTEzNTY4LCJleHAiOjE1NzgxMTcxNjgsIm5iZiI6MTU3ODExMzU2OCwianRpIjoiQmpIamxEMmtQd1M5VmdIcyIsInN1YiI6NCwicHJ2IjoiZjY0ZDQ4YTZjZWM3YmRmYTdmYmY4OTk0NTRiNDg4YjNlNDYyNTIwYSIsInJvbGUiOiJ1c2VycyJ9.LYLcZIV-Ryq2d7GPl6cy7pDrUosFaI-xl_PcOMxpupU",
            2,
            data
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(subscriber)
    }
}