package com.soict.hoangviet.baseproject.data.network

import com.soict.hoangviet.baseproject.data.network.response.BaseEntityResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @GET("/v1/category")
    fun getCategory(): Call<BaseEntityResponse<*>>

    @POST("/v1/user_update_profile")
    @Multipart
    fun updateProfile(
        @Part fileImage: MultipartBody.Part
    ) : Call<BaseEntityResponse<*>>
}