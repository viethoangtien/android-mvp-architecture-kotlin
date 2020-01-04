package com.soict.hoangviet.baseproject.data.network

import com.google.gson.annotations.SerializedName
import com.soict.hoangviet.baseproject.data.network.api.ApiException

class ApiError {
    @SerializedName("status")
    var statusCode: Int = ApiConstant.HttpStatusCode.UNKNOWN
    @SerializedName("msg")
    var message: String = ApiConstant.HttpMessage.ERROR_TRY_AGAIN

    fun getApiException(): ApiException {
        return ApiException(statusCode, message)
    }
}