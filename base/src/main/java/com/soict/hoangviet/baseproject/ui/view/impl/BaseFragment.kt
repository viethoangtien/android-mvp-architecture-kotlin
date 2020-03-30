package com.soict.hoangviet.baseproject.ui.view.impl

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.soict.hoangviet.baseproject.common.BaseLoadingDialog
import com.soict.hoangviet.baseproject.ui.view.BaseView
import dagger.android.support.AndroidSupportInjection
import java.util.*

abstract class BaseFragment : Fragment(), BaseView {
    private var parentActivity: AppCompatActivity? = null

    override
    fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            parentActivity = context
        }
        (parentActivity as BaseActivity)?.onFragmentAttached()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }

    private fun performDI() = AndroidSupportInjection.inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showLoading() {
        parentActivity?.let {
            BaseLoadingDialog.getInstance(it).showLoadingDialog()
        }
    }

    override fun hideLoading() {
        parentActivity?.let {
            BaseLoadingDialog.getInstance(it).hideLoadingDialog()
        }
    }

    override fun onDetach() {
        super.onDetach()
        (parentActivity as BaseActivity).onFragmentDetached("")
    }

    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    fun setData(data: Map<String, Any>) {
        if (data == null || data.isEmpty()) {
            arguments = Bundle()
            return
        }
        val bundle = Bundle()
        for ((key, value) in data) {
            when (value) {
                is String -> bundle.putString(key, value)
                is Double -> bundle.putDouble(key, value)
                is Int -> bundle.putInt(key, value)
                is Float -> bundle.putFloat(key, value)
                is Boolean -> bundle.putBoolean(key, value)
                is Parcelable -> bundle.putParcelable(key, value)
                is Array<*> -> {
                    if (value.size > 0) {
                        when (value[0]) {
                            is String -> bundle.putStringArray(key, value as Array<String>?)
                        }
                    }
                }
                is ArrayList<*> -> {
                    if (value.size > 0) {
                        when (value[0]) {
                            is String -> bundle.putStringArrayList(key, value as ArrayList<String>)
                            is Parcelable -> bundle.putParcelableArrayList(key, value as ArrayList<Parcelable>)
                        }
                    }
                }
            }
        }
        arguments = bundle
    }

}