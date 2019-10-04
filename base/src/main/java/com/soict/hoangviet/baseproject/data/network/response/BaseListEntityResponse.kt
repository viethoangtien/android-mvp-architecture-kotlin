package com.soict.hoangviet.baseproject.data.network.response

import com.google.gson.annotations.SerializedName

class BaseListEntityResponse<T> : BaseResponse() {
    @SerializedName("data")
    private var data: ArrayList<T>? = null
}