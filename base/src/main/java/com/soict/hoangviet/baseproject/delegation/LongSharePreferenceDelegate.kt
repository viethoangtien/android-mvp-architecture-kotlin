package com.soict.hoangviet.baseproject.delegation

import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LongSharePreferenceDelegate(
    private val sharedPreferences: SharePreference
) : ReadWriteProperty<Any, Long> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Long {
        return sharedPreferences.get(property.name, Long::class.java)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
        return sharedPreferences.put(property.name, value)
    }

}