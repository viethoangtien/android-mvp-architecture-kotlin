package com.soict.hoangviet.baseproject.extension

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.soict.hoangviet.baseproject.R
import com.soict.hoangviet.baseproject.ui.view.impl.BaseFragment

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commitAllowingStateLoss()
}

fun AppCompatActivity.addFragment(
    frameId: Int,
    fragment: Fragment,
    data: Map<String, Any> = mutableMapOf(),
    hasAnimation: Boolean = true
) {
    supportFragmentManager.inTransaction {
        (fragment as BaseFragment).setData(data)
        if (hasAnimation) {
            setCustomAnimations(R.anim.trans_right_in, R.anim.trans_right_in)
        } else {
            setCustomAnimations(R.anim.animation_none, R.anim.animation_none)
        }
        add(frameId, fragment)
    }
}

fun AppCompatActivity.addAndToBackStack(
    frameId: Int,
    fragment: Fragment,
    data: Map<String, Any> = mutableMapOf(),
    hasAnimation: Boolean = true
) {
    supportFragmentManager.inTransaction {
        (fragment as BaseFragment).setData(data)
        if (hasAnimation) {
            setCustomAnimations(R.anim.trans_right_in, R.anim.trans_right_in)
        } else {
            setCustomAnimations(R.anim.animation_none, R.anim.animation_none)
        }
        add(frameId, fragment)
        addToBackStack(null)
    }
}

fun AppCompatActivity.replaceFragment(
    frameId: Int,
    fragment: Fragment,
    data: Map<String, Any> = mutableMapOf(),
    hasAnimation: Boolean = true
) {
    supportFragmentManager.inTransaction {
        (fragment as BaseFragment).setData(data)
        if (hasAnimation) setCustomAnimations(
            R.anim.trans_left_in, R.anim.trans_left_out,
            R.anim.trans_right_in, R.anim.trans_right_out
        )
        replace(frameId, fragment)
    }
}

fun AppCompatActivity.replaceAndToBackStack(frameId: Int, fragment: Fragment) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        addToBackStack(null)
    }
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction {
        remove(fragment)
    }
}

fun AppCompatActivity.inResourceString(func: Resources.() -> String): String {
    return resources.func()
}

fun AppCompatActivity.inResourceDrawable(func: Resources.() -> Drawable): Drawable {
    return resources.func()
}

/**
 * Extension method to provide hide keyboard for [Activity].
 */
fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}