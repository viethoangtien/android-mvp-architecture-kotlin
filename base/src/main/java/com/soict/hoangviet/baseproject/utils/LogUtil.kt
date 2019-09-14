package com.soict.hoangviet.baseproject.utils

import android.util.Log
import java.lang.StringBuilder

object LogUtil {
    private var isDebug: Boolean = false

    fun init(isDebug: Boolean) {
        this.isDebug = isDebug
    }

    /**
     *Logs a debug message
     */
    fun d(objects: Any) {
        if (isDebug) {
            Log.d(LogUtil::class.java.simpleName, objects.toString())
        }
    }

    fun d(vararg objects: Any) {
        if (isDebug) {
            val stringBuilder = StringBuilder()
            objects.forEachIndexed { index, any ->
                stringBuilder.append(any)
                if (index != objects.size - 1) {
                    stringBuilder.append(" ")
                }
            }
            Log.d(LogUtil::class.java.simpleName, stringBuilder.toString())
        }
    }

    /**
     * Logs an error message
     */
    fun e(objects: Any) {
        if (isDebug) {
            Log.e(LogUtil::class.java.simpleName, objects.toString())
        }
    }

    fun e(vararg objects: Any) {
        if (isDebug) {
            val stringBuilder = StringBuilder()
            objects.forEachIndexed { index, any ->
                stringBuilder.append(any)
                if (index != objects.size - 1) {
                    stringBuilder.append(" ")
                }
            }
            Log.e(LogUtil::class.java.simpleName, stringBuilder.toString())
        }
    }

    /**
     * Logs an information message
     */
    fun i(objects: Any) {
        if (isDebug) {
            Log.i(LogUtil::class.java.simpleName, objects.toString())
        }
    }

    fun i(vararg objects: Any) {
        if (isDebug) {
            val stringBuilder = StringBuilder()
            objects.forEachIndexed { index, any ->
                stringBuilder.append(any)
                if (index != objects.size - 1) {
                    stringBuilder.append(" ")
                }
            }
            Log.i(LogUtil::class.java.simpleName, stringBuilder.toString())
        }
    }

    /**
     * Logs a warning message
     */
    fun w(objects: Any) {
        if (isDebug) {
            Log.w(LogUtil::class.java.simpleName, objects.toString())
        }
    }

    fun w(vararg objects: Any) {
        if (isDebug) {
            val stringBuilder = StringBuilder()
            objects.forEachIndexed { index, any ->
                stringBuilder.append(any)
                if (index != objects.size - 1) {
                    stringBuilder.append(" ")
                }
            }
            Log.w(LogUtil::class.java.simpleName, stringBuilder.toString())
        }
    }

    /**
     * Logs a verbose message
     */
    fun v(objects: Any) {
        if (isDebug) {
            Log.v(LogUtil::class.java.simpleName, objects.toString())
        }
    }

    fun v(vararg objects: Any) {
        if (isDebug) {
            val stringBuilder = StringBuilder()
            objects.forEachIndexed { index, any ->
                stringBuilder.append(any)
                if (index != objects.size - 1) {
                    stringBuilder.append(" ")
                }
            }
            Log.v(LogUtil::class.java.simpleName, stringBuilder.toString())
        }
    }

}