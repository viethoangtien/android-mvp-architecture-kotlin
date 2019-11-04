package com.soict.hoangviet.baseproject.data.network

import com.soict.hoangviet.baseproject.data.network.response.BaseEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
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
    fun dynamicUrl(@Url url: String): Call<BaseEntityResponse<*>>

    @GET("/v1/drivers/{${ApiConstant.RequestParam.ID}}/posts")
    @Headers("Content-Type:application/json")
    fun getListDriver(@Header(ApiConstant.RequestParam.AUTHORIZATION_HEADER) authToken: String, @Path(ApiConstant.RequestParam.ID) id: Int, @QueryMap data: MutableMap<String, Any>): Single<Response<BaseListEntityResponse<TestResponse>>>
}