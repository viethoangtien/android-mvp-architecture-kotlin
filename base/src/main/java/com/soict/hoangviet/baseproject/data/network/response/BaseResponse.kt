package com.soict.hoangviet.baseproject.data.network.response

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("status")
    private var status: Int = 0
    @SerializedName("msg")
    private var msg: String = ""
}