package com.soict.hoangviet.baseproject.data.sharepreference

interface SharePreference {
    fun setString(key: String, value: String)
    fun getString(key: String): String
    fun setBoolean(key: String, value: Boolean)
    fun getBoolean(key: String): Boolean
    fun setArrayListString(arrayName: String, list: ArrayList<String>)
    fun getArrayListString(arrayName: String): ArrayList<String>
    fun onDestroy()
}