package com.soict.hoangviet.baseproject.data.network

import com.soict.hoangviet.baseproject.data.network.response.BaseEntityResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/category")
    fun getCategory(): Call<BaseEntityResponse<*>>
}