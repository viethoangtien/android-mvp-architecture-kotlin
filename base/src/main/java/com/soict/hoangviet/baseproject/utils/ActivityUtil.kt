package com.soict.hoangviet.baseproject.utils

import android.content.Context
import android.content.res.Resources
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.request.RequestOptions
import com.soict.hoangviet.baseproject.R
import com.soict.hoangviet.baseproject.module.GlideApp


object ActivityUtil {
    fun addFragmentToActivity(mFragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        mFragmentManager.beginTransaction()
            .add(frameId, fragment)
            .commit()
    }
}