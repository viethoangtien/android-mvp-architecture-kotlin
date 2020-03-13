package com.soict.hoangviet.baseproject.delegation

import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class IntSharePreferenceDelegate(
    private val sharedPreferences: SharePreference
) : ReadWriteProperty<Any, Int> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return sharedPreferences.get(property.name, Int::class.java)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        return sharedPreferences.put(property.name, value)
    }

}