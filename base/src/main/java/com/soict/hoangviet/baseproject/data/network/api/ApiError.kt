package com.soict.hoangviet.baseproject.data.network

import com.soict.hoangviet.baseproject.R
import com.soict.hoangviet.baseproject.application.BaseApplication
import com.soict.hoangviet.baseproject.data.network.api.ApiException

class ApiError() {
    private var statusCode: Int = ApiConstant.HttpStatusCode.UNKNOWN
    private var message: String = ApiConstant.HttpMessage.ERROR_TRY_AGAIN

    fun getApiException(): ApiException {
        return ApiException(statusCode, message)
    }
}