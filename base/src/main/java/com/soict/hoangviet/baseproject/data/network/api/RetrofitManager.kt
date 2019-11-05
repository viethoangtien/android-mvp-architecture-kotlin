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
}