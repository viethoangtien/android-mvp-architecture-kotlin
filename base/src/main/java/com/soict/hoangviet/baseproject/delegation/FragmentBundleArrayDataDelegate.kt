package com.soict.hoangviet.baseproject.delegation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.soict.hoangviet.baseproject.extension.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentBundleArrayDataDelegate<T : Any> : ReadWriteProperty<Fragment, ArrayList<T>> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>
    ): ArrayList<T> {
        val key = property.name
        return thisRef.arguments
            ?.get(key) as? ArrayList<T>
            ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override fun setValue(
        thisRef: Fragment,
        property: KProperty<*>, value: ArrayList<T>
    ) {
        val args = thisRef.arguments
            ?: Bundle().also(thisRef::setArguments)
        val key = property.name
        args.put(key, value)
    }

}