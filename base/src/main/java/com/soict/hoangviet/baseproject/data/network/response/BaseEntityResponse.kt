package com.soict.hoangviet.baseproject.data.network.response

import com.google.gson.annotations.SerializedName

class BaseEntityResponse<T> : BaseResponse() {
    @SerializedName("data")
    private var data: T? = null
}