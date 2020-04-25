package com.soict.hoangviet.baseproject.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.soict.hoangviet.baseproject.extension.inflate

abstract class BaseCustomViewConstrainLayout : ConstraintLayout {
    abstract val layoutRes: Int
    protected open val styleRes: IntArray? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setLayout()
        initListener()
        initView()
        initData()
        attrs?.let {
            initStyleable(it)
        }
    }

    private fun initStyleable(it: AttributeSet) {
        if (styleRes != null && styleRes?.isNotEmpty()!!) {
            val typeArray = context.obtainStyledAttributes(it, styleRes, 0, 0)
            initDataFromStyleable(typeArray)
            typeArray.recycle()
        }
    }

    protected open fun initDataFromStyleable(typeArray: TypedArray?) {
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initListener()

    private fun setLayout() {
        context.inflate(layoutRes, this, true)
    }


}