package com.soict.hoangviet.baseproject.ui.presenter.impl

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.soict.hoangviet.baseproject.data.network.ApiConstant
import com.soict.hoangviet.baseproject.data.network.ApiError
import com.soict.hoangviet.baseproject.data.network.api.ApiException
import com.soict.hoangviet.baseproject.data.network.api.NetworkConnectionInterceptor
import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import com.soict.hoangviet.baseproject.ui.interactor.BaseInteractor
import com.soict.hoangviet.baseproject.ui.presenter.BasePresenter
import com.soict.hoangviet.baseproject.ui.view.BaseView
import com.soict.hoangviet.baseproject.utils.ToastUtil
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.io.IOException
import java.lang.IllegalStateException
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

abstract class BasePresenterImpl<V : BaseView, I : BaseInteractor>
internal constructor(
    protected var mInterator: I?,
    protected var mAppSharePreference: SharePreference?
) : BasePresenter<V, I> {
    private var mView: V? = null
    protected val isAttached get() = mView != null
    protected val mCompositeDisposable = CompositeDisposable()

    override fun onAttach(view: V?) {
        mView = view
    }

    override fun getView(): V? {
        return mView
    }

    override fun onDetach() {
        mView = null
        mInterator = null
        mCompositeDisposable.dispose()
    }

    private fun <T> gsonFromJson(json: String?, classOfT: Class<T>): T {
        return Gson().fromJson(json, classOfT) ?: throw Exception()
    }

    protected fun handleThrowable(throwable: Throwable) {
        var apiError: ApiError? = null
        var apiException: ApiException? = null
        when (throwable) {
            is NetworkConnectionInterceptor.NoConnectivityException -> {
                apiError = ApiError(throwable.message.toString())
            }
            is HttpException -> {
                //convert to apiError
                try {
                    apiError = gsonFromJson(
                        throwable.response().errorBody().toString(),
                        ApiError::class.java
                    )
                } catch (jfe: JsonParseException) {
                    apiError = ApiError(ApiConstant.HttpMessage.TIME_OUT)
                } catch (ioe: IOException) {
                    apiError = ApiError(ApiConstant.HttpMessage.TIME_OUT)
                } catch (ile: IllegalStateException) {
                    apiError = ApiError(ApiConstant.HttpMessage.TIME_OUT)
                }
            }
            is TimeoutException, is SocketException, is UnknownHostException -> {
                apiError = ApiError(ApiConstant.HttpMessage.TIME_OUT)
            }
        }
        apiError?.let {
            apiException = it.getApiException()
        } ?: kotlin.run {
            apiException = ApiException()
        }
        ToastUtil.show(apiException?.message ?: ApiConstant.HttpMessage.ERROR_TRY_AGAIN)
    }
}