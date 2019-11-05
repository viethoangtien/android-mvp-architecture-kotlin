package com.soict.hoangviet.baseproject.data.network.api

import com.soict.hoangviet.baseproject.data.network.ICallBack
import com.soict.hoangviet.baseproject.data.network.response.BaseEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RetrofitManager private constructor() : BaseRetrofit() {
    companion object {
        private var instance: RetrofitManager? = null
        fun getInstance(): RetrofitManager {
            if (instance == null) instance = RetrofitManager()
            return instance!!
        }
    }

    fun getCategory(callBack: ICallBack<BaseEntityResponse<*>>) {
        val call = getApiService().getCategory()
        callRequest(call, callBack)
    }

    fun getListDriverPost(
        id: Int,
        data: MutableMap<String, Any>,
        callBack: ICallBack<BaseListEntityResponse<TestResponse>>
    ): Disposable {
        val subscriber = getSubscriber(callBack)
        return getApiService().getListDriver(
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGktc3RhZ2luZy50aW1kYXR4ZS5jb21cL3YxXC9kcml2ZXJfc2Vzc2lvbnMiLCJpYXQiOjE1NzI5MzUwNTYsImV4cCI6MTU3MjkzODY1NiwibmJmIjoxNTcyOTM1MDU2LCJqdGkiOiJKREFkZnNxRk1WZGlhT1ZDIiwic3ViIjoyLCJwcnYiOiIxYjJlY2ZkMDAxMzc1ZDIxZWFhMmQ0MDkyYjM2OTYzYjk1NjBmNjgxIiwicm9sZSI6ImRyaXZlcnMifQ.pyju_qUk9I84yHMsrRL4QfsZ3skCHbA_aZgKiCgEbmQ",
            id,
            data
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(subscriber)
    }
}