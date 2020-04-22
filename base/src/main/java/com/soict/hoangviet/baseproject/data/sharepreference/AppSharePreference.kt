package com.soict.hoangviet.baseproject.data.sharepreference

import android.content.Context
import android.content.SharedPreferences
import com.soict.hoangviet.baseproject.utils.Define

class AppSharePreference constructor(var context: Context?) : SharePreference {

    val mPrefs: SharedPreferences
        get() = context?.getSharedPreferences(Define.PREF_NAME, Context.MODE_PRIVATE)!!

    override fun <T> put(key: String, value: T) {
        val editor = mPrefs.edit()
        when (value) {
            is Boolean -> editor.putBoolean(key, value)
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            else -> throw IllegalStateException("Type of value $key is not supported")
        }
        editor.apply()
    }

    override fun <T> get(key: String, clazz: Class<T>): T {
        return when (clazz) {
            Boolean::class.java -> mPrefs.getBoolean(key, false) as T
            String::class.java -> mPrefs.getString(key, "") as T
            Int::class.java -> mPrefs.getInt(key, -1) as T
            Long::class.java -> mPrefs.getLong(key, -1) as T
            Float::class.java -> mPrefs.getFloat(key, -1.0f) as T
            else -> throw IllegalStateException("Type of value $key is not supported")
        }
    }

    override fun setArrayListString(arrayName: String, list: ArrayList<String>) {
        put("${arrayName}_size", list.size)
        list.forEachIndexed { index, item ->
            put("${arrayName}_${index}", item)
        }
    }

    override fun getArrayListString(arrayName: String): ArrayList<String> {
        val list = arrayListOf<String>()
        for (index in 0..get("${arrayName}_size", Int::class.java)) {
            list.add(get("${arrayName}_${index}", String::class.java))
        }
        return list
    }

    override fun onDestroy() {
        if (context != null) {
            context = null
        }
    }

}