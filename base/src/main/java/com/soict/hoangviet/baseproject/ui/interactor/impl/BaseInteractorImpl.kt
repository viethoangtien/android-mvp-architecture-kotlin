package com.soict.hoangviet.baseproject.ui.interactor.impl

import com.google.gson.Gson
import com.soict.hoangviet.baseproject.ui.interactor.BaseInteractor
import okhttp3.MultipartBody
import okhttp3.RequestBody

open class BaseInteractorImpl
internal constructor() : BaseInteractor {

    protected fun createRequestBody(request: Any): RequestBody {
        var rawString: String? = null
        try {
            rawString = Gson().toJson(request)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return RequestBody.create(MultipartBody.FORM, rawString ?: "")
    }
}