package com.soict.hoangviet.baseproject.delegation

import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class BooleanSharePreferenceDelegate constructor(
    private val mSharedPreferences: SharePreference
) : ReadWriteProperty<Any, Boolean> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return mSharedPreferences.get(property.name, Boolean::class.java)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        return mSharedPreferences.put(property.name, value)
    }

}