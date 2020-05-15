package com.soict.hoangviet.baseproject.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.hideFragmentByTag(tag: String) {
    val ft = supportFragmentManager.beginTransaction()
    val frag = supportFragmentManager.findFragmentByTag(tag)
    frag?.let {
        ft.remove(it)
    }
    ft.addToBackStack(null)
}

fun AppCompatActivity.addFragment(
    frameId: Int,
    fragment: Fragment,
    bundle: Bundle? = null,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.inTransaction {
        if (addToBackStack) {
            addToBackStack(fragment::class.java.simpleName)
        }
        bundle?.let { fragment.arguments = it }
        add(frameId, fragment)
    }
}

fun AppCompatActivity.replaceFragment(
    frameId: Int,
    fragment: Fragment,
    bundle: Bundle? = null,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.inTransaction {
        if (addToBackStack) {
            addToBackStack(fragment::class.java.simpleName)
        }
        bundle?.let { fragment.arguments = it }
        replace(frameId, fragment)
    }
}

fun Fragment.removeFragment() {
    requireActivity().supportFragmentManager.inTransaction {
        remove(this@removeFragment)
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