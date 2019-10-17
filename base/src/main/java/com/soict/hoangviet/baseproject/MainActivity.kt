package com.soict.hoangviet.baseproject

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soict.hoangviet.baseproject.common.BasePhotoActivity
import com.soict.hoangviet.baseproject.custom.AmountTextWatcher
import com.soict.hoangviet.baseproject.data.network.api.RetrofitManager
import com.soict.hoangviet.baseproject.extension.inResourceString
import com.soict.hoangviet.baseproject.module.GlideApp
import kotlinx.android.synthetic.main.activity_test.*
import java.io.File

class MainActivity : BasePhotoActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        openGallery()
    }

    override fun onTakeImageFileCaptureSuccess(cameraFilePath: String) {
        imv_test.setImageURI(Uri.parse(cameraFilePath))
    }

    override fun onTakeAbsolutePathImageSuccess(absoluteFilePathImage: String) {
        GlideApp.with(this)
                .load(absoluteFilePathImage)
                .into(imv_test)
    }
}