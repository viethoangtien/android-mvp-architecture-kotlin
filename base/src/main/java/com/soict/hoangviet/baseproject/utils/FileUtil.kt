package com.soict.hoangviet.baseproject.utils

import android.net.Uri
import java.io.File

object FileUtil {
    fun getFile(fileUri: Uri): File {
        return File(fileUri.path)
    }

    fun getExtensionsByPath(path: String): String {
        return when {
            path.contains(".doc") || path.contains(".docx") -> {
                // Word document
                "application/msword"
            }
            path.contains(".pdf") -> {
                // PDF file
                "application/pdf"
            }
            path.contains(".ppt") || path.contains(".pptx") -> {
                // Powerpoint file
                "application/vnd.ms-powerpoint"
            }
            path.contains(".xls") || path.contains(".xlsx") -> {
                // Excel file
                "application/vnd.ms-excel"
            }
            path.contains(".zip") || path.contains(".rar") -> {
                // WAV audio file
                "application/x-wav"
            }
            path.contains(".rtf") -> {
                // RTF file
                "application/rtf"
            }
            path.contains(".wav") || path.contains(".mp3") -> {
                // WAV audio file
                "audio/x-wav"
            }
            path.contains(".gif") -> {
                // GIF file
                "image/gif"
            }
            path.contains(".jpg") || path.contains(".jpeg") || path.contains(
                ".png"
            ) -> {
                // JPG file
                "image/jpeg"
            }
            path.contains(".txt") -> {
                // Text file
                "text/plain"
            }
            path.contains(".3gp") || path.contains(".mpg") || path.contains(".mpeg") || path.contains(
                ".mpe"
            ) || path.contains(".mp4") || path.contains(".avi") -> {
                // Video files
                "video/*"
            }
            else -> {
                //if you want you can also define the intent type for any other file
                //additionally use else clause below, to manage other unknown extensions
                //in this case, Android will show all applications installed on the device
                //so you can choose which application to use
                "*/*"
            }
        }
    }
}