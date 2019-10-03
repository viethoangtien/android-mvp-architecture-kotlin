package com.soict.hoangviet.baseproject.data.network.api

import com.soict.hoangviet.baseproject.data.network.ApiConstant
import com.soict.hoangviet.baseproject.data.network.ICallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitManager : BaseRetrofit() {
    fun <T> callRequest(call: Call<T>, iCallBack: ICallBack<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                handleResponse(iCallBack,response)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
            }
        })
    }

    private fun <T> handleResponse(iCallBack: ICallBack<T>, response: Response<T>) {
        when{
            response.code()==ApiConstant.HttpStatusCode.OK->{iCallBack.onSuccess(response.body()!!)}
            response.code()==ApiConstant.HttpStatusCode.CREATED->{iCallBack.onSuccess(response.body()!!)}
            response.code()==ApiConstant.HttpStatusCode.UNAUTHORIZED->{ }
        }
    }
}