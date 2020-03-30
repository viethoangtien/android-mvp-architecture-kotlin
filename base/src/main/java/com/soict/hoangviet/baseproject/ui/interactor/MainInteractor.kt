package com.soict.hoangviet.baseproject.ui.interactor

import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import io.reactivex.Single

interface MainInteractor : BaseInteractor {
    fun fetchListDriver(data: MutableMap<String, Any>): Single<BaseListEntityResponse<TestResponse>>
}