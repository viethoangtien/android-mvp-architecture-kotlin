package com.soict.hoangviet.baseproject.utils

import com.soict.hoangviet.baseproject.data.network.ApiError
import com.soict.hoangviet.baseproject.data.network.api.BaseRetrofit
import retrofit2.Response
import java.io.IOException

object ErrorUtil {
    fun parseError(response: Response<*>): ApiError {
        val annotation = object : Annotation {}
        val converter = BaseRetrofit.provideRetrofit()
            .responseBodyConverter<ApiError>(ApiError::class.java, arrayOf(annotation))
        return try {
            converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            ApiError()
        }
    }
}