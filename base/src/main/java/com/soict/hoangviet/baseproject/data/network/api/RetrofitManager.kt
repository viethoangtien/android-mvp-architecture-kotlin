package com.soict.hoangviet.baseproject.data.network.api

import com.soict.hoangviet.baseproject.data.network.ApiConstant
import com.soict.hoangviet.baseproject.data.network.ICallBack
import com.soict.hoangviet.baseproject.data.network.response.BaseEntityResponse
import com.soict.hoangviet.baseproject.utils.ErrorUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitManager : BaseRetrofit() {
    companion object {
        private lateinit var instance: RetrofitManager
        fun getInstance(): RetrofitManager {
            if (instance == null) instance = RetrofitManager()
            return instance
        }
    }

    private fun <T> callRequest(call: Call<T>, iCallBack: ICallBack<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                handleResponse(iCallBack, response)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
            }
        })
    }

    private fun <T> handleResponse(iCallBack: ICallBack<T>, response: Response<T>) {
        when {
            response.code() == ApiConstant.HttpStatusCode.OK -> {
                iCallBack.onSuccess(response.body()!!)
            }
            response.code() == ApiConstant.HttpStatusCode.CREATED -> {
                iCallBack.onSuccess(response.body()!!)
            }
            response.code() == ApiConstant.HttpStatusCode.UNAUTHORIZED -> {
            }
            else -> {
                handleError(iCallBack, response)
            }
        }
    }

    private fun <T> handleError(iCallBack: ICallBack<T>, response: Response<T>) {
        val apiError = ErrorUtil.parseError(response)
        iCallBack.onError(apiError)
    }

    fun getCategory(iCallBack: ICallBack<BaseEntityResponse<*>>) {
        val call = getApiService().getCategory()
        callRequest(call, iCallBack)
    }
}