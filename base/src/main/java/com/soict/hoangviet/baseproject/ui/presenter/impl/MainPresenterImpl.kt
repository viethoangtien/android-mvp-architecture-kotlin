package com.soict.hoangviet.baseproject.ui.presenter.impl

import com.soict.hoangviet.baseproject.data.network.ICallBack
import com.soict.hoangviet.baseproject.data.network.api.ApiException
import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import com.soict.hoangviet.baseproject.ui.interactor.BaseInterator
import com.soict.hoangviet.baseproject.ui.interactor.MainInteractor
import com.soict.hoangviet.baseproject.ui.presenter.MainPresenter
import com.soict.hoangviet.baseproject.ui.view.BaseView
import com.soict.hoangviet.baseproject.utils.ToastUtil
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenterImpl<V : BaseView, I : BaseInterator>
@Inject internal constructor(mInteractor: I, compositeDisposable: CompositeDisposable) :
    BasePresenterImpl<V, I>(mInterator = mInteractor, mCompositeDisposable = compositeDisposable),
    MainPresenter<V, I> {
    private var page = 1
    private var totalPage = 0
    override fun fetchListDriver() {
        val data: MutableMap<String, Any> = mutableMapOf()
        data["status"] = ""
        data["empty_seat"] = ""
        data["start_point"] = ""
        data["start_time"] = ""
        data["end_point"] = ""
        data["limit"] = 6
        data["page"] = page
        (mInterator as MainInteractor)?.let {
            mCompositeDisposable.add(it.fetchListDriver(
                data, object : ICallBack<BaseListEntityResponse<TestResponse>> {
                    override fun onSuccess(result: BaseListEntityResponse<TestResponse>) {
                        ToastUtil.show("Good Job!")
                    }

                    override fun onError(e: ApiException) {
                        ToastUtil.show("Error Happen. Please try again!")
                    }
                }
            ))
        }
    }
}