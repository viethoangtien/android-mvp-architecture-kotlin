package com.soict.hoangviet.baseproject.data.network.api

import com.google.gson.Gson
import com.soict.hoangviet.baseproject.BuildConfig
import com.soict.hoangviet.baseproject.application.BaseApplication
import com.soict.hoangviet.baseproject.data.network.ApiConstant
import com.soict.hoangviet.baseproject.data.network.ApiError
import com.soict.hoangviet.baseproject.data.network.ApiService
import com.soict.hoangviet.baseproject.data.network.ICallBack
import io.reactivex.observers.DisposableSingleObserver
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

open class BaseRetrofit {
    companion object {
        private var apiService: ApiService? = null
        private var retrofit: Retrofit? = null
        private var logging = HttpLoggingInterceptor().setLevel(ApiConstant.LoggingLevel.BODY)

        private fun provideOkHttpClient(): OkHttpClient {
            var mOkHttpClientBuilder = OkHttpClient.Builder()
            mOkHttpClientBuilder.connectTimeout(ApiConstant.Timeout.CONNECT, TimeUnit.SECONDS)
            mOkHttpClientBuilder.readTimeout(ApiConstant.Timeout.READ, TimeUnit.SECONDS)
            mOkHttpClientBuilder.writeTimeout(ApiConstant.Timeout.WRITE, TimeUnit.SECONDS)
            mOkHttpClientBuilder.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val original = chain.request()
                    val request = original.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build()
                    return chain.proceed(request)
                }
            }).addInterceptor(NetworkConnectionInterceptor(BaseApplication().getContext()))
            if (BuildConfig.DEBUG) {
                mOkHttpClientBuilder?.let {
                    if (!it.interceptors().contains(logging)) {
                        it.addInterceptor(logging)
                    }
                }
            }
            return mOkHttpClientBuilder.build()
        }

        private fun provideRetrofit(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        fun getApiService(): ApiService {
            if (apiService == null) {
                apiService = provideRetrofit().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}