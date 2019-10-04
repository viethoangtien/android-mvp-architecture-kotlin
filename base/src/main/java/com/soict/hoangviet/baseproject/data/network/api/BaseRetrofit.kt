package com.soict.hoangviet.baseproject.data.network.api

import com.google.gson.Gson
import com.soict.hoangviet.baseproject.BuildConfig
import com.soict.hoangviet.baseproject.data.network.ApiConstant
import com.soict.hoangviet.baseproject.data.network.ApiService
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseRetrofit {
    companion object {
        private lateinit var apiService: ApiService
        private lateinit var retrofit: Retrofit

        private var logging = HttpLoggingInterceptor().setLevel(ApiConstant.LoggingLevel.BODY)

        private fun provideOkHttpClient(): OkHttpClient {
            var mOkHttpClientBuilder = OkHttpClient.Builder()
            mOkHttpClientBuilder.connectTimeout(ApiConstant.Timeout.CONNECT, TimeUnit.SECONDS)
            mOkHttpClientBuilder.readTimeout(ApiConstant.Timeout.READ, TimeUnit.SECONDS)
            mOkHttpClientBuilder.writeTimeout(ApiConstant.Timeout.WRITE, TimeUnit.SECONDS)
            mOkHttpClientBuilder.callTimeout(ApiConstant.Timeout.CALL, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                mOkHttpClientBuilder?.let {
                    if (!it.interceptors().contains(logging)) {
                        it.addInterceptor(logging)
                    }
                }
            }
            return mOkHttpClientBuilder.build()
        }

        fun provideRetrofit(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun getApiService(): ApiService {
            if (apiService == null) {
                apiService = provideRetrofit().create(ApiService::class.java)
            }
            return apiService
        }
    }

    protected fun createRequestBody(request: Any): RequestBody {
        val rawString = Gson().toJson(request)
        return RequestBody.create(MultipartBody.FORM, rawString ?: "")
    }
}