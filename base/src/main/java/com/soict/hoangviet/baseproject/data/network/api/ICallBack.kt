package com.soict.hoangviet.baseproject.data.network

interface ICallBack<T> {
    fun onSuccess(result: T)
    fun onError(e: ApiException)
}