package com.soict.hoangviet.baseproject.ui.presenter.impl

import com.soict.hoangviet.baseproject.data.network.ICallBack
import com.soict.hoangviet.baseproject.data.network.api.ApiException
import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import com.soict.hoangviet.baseproject.ui.interactor.MainInteractor
import com.soict.hoangviet.baseproject.ui.presenter.MainPresenter
import com.soict.hoangviet.baseproject.ui.view.MainView
import com.soict.hoangviet.baseproject.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_test.*

class MainPresenterImpl(mView: MainView, mMainInteractor: MainInteractor) : BasePresenterImpl<MainView, MainInteractor>(mView, mMainInteractor), MainPresenter {
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
        mInterator?.let {
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