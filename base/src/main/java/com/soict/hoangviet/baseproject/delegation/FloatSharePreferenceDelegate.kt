package com.soict.hoangviet.baseproject.delegation

import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FloatSharePreferenceDelegate(
    private val sharedPreferences: SharePreference
) : ReadWriteProperty<Any, Float> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Float {
        return sharedPreferences.get(property.name, Float::class.java)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) {
        return sharedPreferences.put(property.name, value)
    }

}