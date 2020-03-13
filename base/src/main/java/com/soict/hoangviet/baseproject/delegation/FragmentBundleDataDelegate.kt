package com.soict.hoangviet.baseproject.delegation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.soict.hoangviet.baseproject.extension.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentBundleDataDelegate<T : Any> : ReadWriteProperty<Fragment, T> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = property.name
        return thisRef.arguments?.get(key) as? T
            ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val key = property.name
        val args = thisRef.arguments ?: Bundle().also { thisRef::setArguments } //Awesome
        args.put(key, value)
    }

}