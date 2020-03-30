package com.soict.hoangviet.baseproject.ui.interactor.impl

import com.soict.hoangviet.baseproject.data.network.api.Repository
import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import com.soict.hoangviet.baseproject.ui.interactor.MainInteractor
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainInteractorImpl
@Inject
internal constructor(mRepository: Repository) :
    BaseInteractorImpl(repository = mRepository), MainInteractor {
    override fun fetchListDriver(
        data: MutableMap<String, Any>
    ): Single<BaseListEntityResponse<TestResponse>> {
        return repository.getListDriver(2, data)
    }
}