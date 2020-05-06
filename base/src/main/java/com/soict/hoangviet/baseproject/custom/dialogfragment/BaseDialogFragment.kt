package com.soict.hoangviet.baseproject.custom.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.soict.hoangviet.baseproject.R

abstract class BaseDialogFragment(
    val cancelable: Boolean = true,
    val isStyleFullScreen: Boolean = false
) : DialogFragment() {
    abstract val mLayoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isStyleFullScreen) setStyle(
            STYLE_NORMAL,
            android.R.style.Theme_Black_NoTitleBar_Fullscreen
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(mLayoutRes, container, false)
        if (!isStyleFullScreen) dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_dialog)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        isCancelable = cancelable
        dialog?.setCanceledOnTouchOutside(cancelable)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    abstract fun initListeners()

    abstract fun initViews()
}