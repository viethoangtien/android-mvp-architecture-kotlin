package com.soict.hoangviet.baseproject.data.network

import com.soict.hoangviet.baseproject.data.network.api.ApiException

interface ICallBack<T> {
    fun onSuccess(result: T)
    fun onError(e: ApiException)
}