package com.soict.hoangviet.baseproject.ui.base.interactor

import com.google.gson.Gson
import com.soict.hoangviet.baseproject.data.network.ApiConstant
import com.soict.hoangviet.baseproject.data.network.ApiError
import com.soict.hoangviet.baseproject.data.network.ICallBack
import com.soict.hoangviet.baseproject.data.network.api.ApiException
import com.soict.hoangviet.baseproject.data.network.api.BaseRetrofit
import com.soict.hoangviet.baseproject.data.network.api.NetworkConnectionInterceptor
import io.reactivex.observers.DisposableSingleObserver
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

open class BaseInteratorImpl() : BaseInterator {

    protected fun createRequestBody(request: Any): RequestBody {
        var rawString: String? = null
        try {
            rawString = Gson().toJson(request)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return RequestBody.create(MultipartBody.FORM, rawString ?: "")
    }

    protected fun <T> getSubscriber(callBack: ICallBack<T>): DisposableSingleObserver<Response<T>> {
        return object : DisposableSingleObserver<Response<T>>() {
            override fun onSuccess(response: Response<T>) {
                handleResponse(callBack, response!!)
            }

            override fun onError(throwable: Throwable) {
                handleFailure(callBack, throwable!!)
            }

        }
    }

    protected fun <T> gsonFromJson(json: String?, classOfT: Class<T>): T {
        return Gson().fromJson(json, classOfT) ?: throw Exception()
    }

    protected fun <T> callRequest(call: Call<T>, callBack: ICallBack<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                handleResponse(callBack, response)
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                handleFailure(callBack, throwable)
            }
        })
    }

    private fun <T> handleFailure(callBack: ICallBack<T>, throwable: Throwable) {
        if (throwable is NetworkConnectionInterceptor.NoConnectivityException) {
            callBack.onError(ApiException(throwable?.let { it.message!! }))
        } else {
            callBack.onError(ApiException(throwable?.let { it.message!! }))
        }
    }

    private fun <T> handleResponse(callBack: ICallBack<T>, response: Response<T>) {
        when {
            response.code() == ApiConstant.HttpStatusCode.OK -> {
                callBack.onSuccess(response.body()!!)
            }
            response.code() == ApiConstant.HttpStatusCode.CREATED -> {
                callBack.onSuccess(response.body()!!)
            }
            response.code() == ApiConstant.HttpStatusCode.UNAUTHORIZED -> {
                handleInvalidToken(callBack, response)
            }
            else -> {
                handleErrorResponse(callBack, response)
            }
        }
    }

    private fun <T> handleInvalidToken(callBack: ICallBack<T>, response: Response<T>) {
        try {
            val mApiError = gsonFromJson(response.errorBody()?.toString(), ApiError::class.java)
            mApiError.statusCode = ApiConstant.ErrorCode.INVALID_TOKEN
            callBack.onError(mApiError.getApiException())
        } catch (e: Exception) {
            callBack.onError(ApiException(ApiConstant.ErrorCode.INVALID_TOKEN))
        } finally {
            logOut()
        }
    }

    private fun <T> handleErrorResponse(callBack: ICallBack<T>, response: Response<T>) {
        try {
            val mApiError = gsonFromJson(response.errorBody()?.toString(), ApiError::class.java)
            callBack.onError(mApiError.getApiException())
        } catch (e: Exception) {
            callBack.onError(ApiException())
        }
    }

    private fun logOut() {
    }

}