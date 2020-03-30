package com.soict.hoangviet.baseproject.data.network.api

import com.soict.hoangviet.baseproject.data.network.ApiService
import com.soict.hoangviet.baseproject.data.network.response.BaseListEntityResponse
import com.soict.hoangviet.baseproject.data.network.response.TestResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(val apiService: ApiService) {
    fun getListDriver(id: Int, data: MutableMap<String, Any>) : Single<BaseListEntityResponse<TestResponse>> {
        return apiService.getListDriver(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGktc3RhZ2luZy50aW1kYXR4ZS5jb21cL3YxXC91c2VyX3Nlc3Npb25zIiwiaWF0IjoxNTc4MTEzNTY4LCJleHAiOjE1NzgxMTcxNjgsIm5iZiI6MTU3ODExMzU2OCwianRpIjoiQmpIamxEMmtQd1M5VmdIcyIsInN1YiI6NCwicHJ2IjoiZjY0ZDQ4YTZjZWM3YmRmYTdmYmY4OTk0NTRiNDg4YjNlNDYyNTIwYSIsInJvbGUiOiJ1c2VycyJ9.LYLcZIV-Ryq2d7GPl6cy7pDrUosFaI-xl_PcOMxpupU",
            id,
            data
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}