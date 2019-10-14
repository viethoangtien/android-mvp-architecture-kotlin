package com.soict.hoangviet.baseproject.utils

import android.net.Uri
import java.io.File

object FileUtil {
    fun getFile(fileUri: Uri): File {
        return File(fileUri.path)
    }
}