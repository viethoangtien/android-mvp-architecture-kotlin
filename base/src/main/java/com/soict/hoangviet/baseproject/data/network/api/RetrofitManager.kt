package com.soict.hoangviet.baseproject.data.network.api

import com.google.gson.Gson
import com.soict.hoangviet.baseproject.data.network.ApiConstant
import com.soict.hoangviet.baseproject.data.network.ApiError
import com.soict.hoangviet.baseproject.data.network.ICallBack
import com.soict.hoangviet.baseproject.data.network.response.BaseEntityResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitManager private constructor() : BaseRetrofit() {
    companion object {
        private lateinit var instance: RetrofitManager
        fun getInstance(): RetrofitManager {
            if (instance == null) instance = RetrofitManager()
            return instance
        }
    }

    fun getCategory(iCallBack: ICallBack<BaseEntityResponse<*>>) {
        val call = getApiService().getCategory()
        callRequest(call, iCallBack)
    }
}