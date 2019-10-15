package com.soict.hoangviet.baseproject.data.network

import com.soict.hoangviet.baseproject.data.network.response.BaseEntityResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/v1/category")
    fun getCategory(): Call<BaseEntityResponse<*>>

    @POST("/v1/user_update_profile")
    @Multipart
    fun updateProfile(
        @Part fileImage: MultipartBody.Part
    ): Call<BaseEntityResponse<*>>

    @HTTP(method = "DELETE", path = "/v1/cart", hasBody = true)
    fun deleteCartdetail(
        @Header(ApiConstant.RequestParam.AUTHORIZATION_HEADER) authToken: String,
        @Body requestBody: RequestBody
    ): Call<BaseEntityResponse<*>>

    @GET
    @Headers("Content-Type:application/json")
    fun dynamicUrl(@Url url: String) : Call<BaseEntityResponse<*>>
}