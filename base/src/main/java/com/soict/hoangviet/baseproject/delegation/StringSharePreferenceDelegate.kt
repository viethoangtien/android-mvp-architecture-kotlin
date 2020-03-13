package com.soict.hoangviet.baseproject.delegation

import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringSharePreferenceDelegate(
    private val sharedPreferences: SharePreference
) : ReadWriteProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return sharedPreferences.get(property.name, String::class.java)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        return sharedPreferences.put(property.name, value)
    }

}