package com.soict.hoangviet.baseproject.utils

import android.net.Uri
import com.soict.hoangviet.baseproject.application.BaseApplication
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

object RetrofitUtil {
    fun prepareFilePart(partNameUpload: String, fileUri: Uri): MultipartBody.Part {
        val file = FileUtil.getFile(fileUri)
        val fileRequestBody = createFileRequestBody(file, fileUri)
        return createFileFormData(partNameUpload, file, fileRequestBody)
    }

    private fun createFileRequestBody(mFile: File, fileUri: Uri): RequestBody {
        return RequestBody.create(
            MediaType.parse(BaseApplication().getContext().contentResolver.getType(fileUri)),
            mFile
        )
    }

    private fun createFileFormData(nameUpload: String, mFile: File, fileRequestBody: RequestBody): MultipartBody.Part {
        return MultipartBody.Part.createFormData(nameUpload, mFile.name, fileRequestBody)
    }

    fun createImageFileRequestBody(mFile: File): RequestBody {
        return RequestBody.create(MediaType.parse("image/*"), mFile)
    }

    fun createFormData(data: String): RequestBody {
        return RequestBody.create(MultipartBody.FORM, data)
    }

    fun createTextPlainFormData(data: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), data)
    }

}