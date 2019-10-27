package com.soict.hoangviet.baseproject.common

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.soict.hoangviet.baseproject.utils.PermissionUtil
import java.io.File
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.soict.hoangviet.baseproject.utils.LogUtil
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


abstract class BasePhotoActivity() : AppCompatActivity() {
    private var cameraFilePath: String? = null
    private var mFileCreateImage: File? = null

    companion object {
        const val REQUEST_CHOOSE_IMAGE = 9001
        const val REQUEST_IMAGE_CAPTURE = 9002
        const val REQUEST_PERMISSION_GALLERY = 9003
        const val REQUEST_PERMISSION_CAPTURE_CAMERA = 9004
        const val REQUEST_SETTING = 9005
    }

    protected fun openCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LogUtil.d("Above Android LOLLIPOP")
            if (PermissionUtil.hasPermission(android.Manifest.permission.CAMERA)) {
                dispatchTakePictureIntent()
            } else {
                LogUtil.d("Request: Permission Camera")
                PermissionUtil.requestPermission(
                        this, android.Manifest.permission.CAMERA,
                        REQUEST_PERMISSION_CAPTURE_CAMERA
                )
            }
        } else {
            LogUtil.d("Below Android LOLLIPOP")
            dispatchTakePictureIntentPreLollipop()
        }
    }

    protected fun openGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (PermissionUtil.hasPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                pickImageFromGallery()
            } else {
                PermissionUtil.requestPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_PERMISSION_GALLERY)
            }
        } else {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        //Create an Intent with action as ACTION_PICK
        val intent = Intent(Intent.ACTION_PICK)
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.type = "image/*"
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        // Launching the Intent
        startActivityForResult(intent, REQUEST_CHOOSE_IMAGE)
    }

    private fun dispatchTakePictureIntent() {
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intentCamera.resolveActivity(packageManager) != null) {
            try {
                mFileCreateImage = createFileImage()
                intentCamera.putExtra(
                        MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(
                                this,
                                resources.getString(com.soict.hoangviet.baseproject.R.string.file_provider),
                                mFileCreateImage!!
                        )
                )
                startActivityForResult(intentCamera, REQUEST_IMAGE_CAPTURE)
            } catch (ioe: IOException) {
                ioe.printStackTrace()
            }
        }
    }

    private fun createFileImage(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        //This is the directory in which the file will be created. This is the default location of Camera photos
        val storageDir = File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM
                ), "Camera"
        )
        val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )
        // Save a file: path for using again
        cameraFilePath = "file://" + image.absolutePath
        return image

    }

    private fun dispatchTakePictureIntentPreLollipop() {
    }

    private fun handleImageUri(uriImage: Uri) {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        // Get the cursor
        val cursor = contentResolver.query(uriImage, filePathColumn, null, null, null, null)
        // Move to first row
        cursor.moveToFirst()
        //Get the column index of MediaStore.Images.Media.DATA
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        //Gets the String value in the column
        val imgDecodableString = cursor.getString(columnIndex)
        onTakeAbsolutePathImageSuccess(imgDecodableString)
        cursor.close()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (permission in permissions) {
            if (PermissionUtil.hasPermission(permission)) {
                when (requestCode) {
                    REQUEST_PERMISSION_GALLERY -> {
                        LogUtil.d("Accept Permission: GALLERY")
                        pickImageFromGallery()
                    }
                    REQUEST_PERMISSION_CAPTURE_CAMERA -> {
                        LogUtil.d("Accept Permission: CAMERA")
                        dispatchTakePictureIntent()
                    }
                }
            } else {
                LogUtil.d("Denied: Permission")
                PermissionUtil.openSettingPermission(this, REQUEST_SETTING)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            cameraFilePath?.let {
                LogUtil.d("Success: Capture Image")
                onTakeImageFileCaptureSuccess(it!!)
            }
            return
        }
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CHOOSE_IMAGE) {
            LogUtil.d("Success: Pick Image")
            handleImageUri(data?.data!!)
            return
        }
    }

    open fun onTakeImageFileCaptureSuccess(cameraFilePath: String){}
    open fun onTakeAbsolutePathImageSuccess(absoluteFilePathImage: String){}
}