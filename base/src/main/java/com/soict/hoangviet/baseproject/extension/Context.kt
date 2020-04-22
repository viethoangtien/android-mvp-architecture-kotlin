package com.soict.hoangviet.baseproject.extension

import android.app.Activity
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.content.Intent.*
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

fun Context.inflate(layoutRes: Int, viewGroup: ViewGroup, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(layoutRes, viewGroup, attachToRoot)
}

/**
 * Extension method to find a device width in pixels
 */
inline val Context.displayWidth: Int
    get() = resources.displayMetrics.widthPixels

/**
 * Extension method to find a device height in pixels
 */
inline val Context.displayHeight: Int
    get() = resources.displayMetrics.heightPixels

/**
 * Extension method to get displayMetrics in Context.displayMetricks
 */
inline val Context.displayMetrics: DisplayMetrics
    get() = resources.displayMetrics

/**
 * Extension method to get LayoutInflater
 */
inline val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

/**
 * Extension method to get a new Intent for an Activity class
 */
inline fun <reified T : Any> Context.intent() = Intent(this, T::class.java)

/**
 * Create an intent for [T] and apply a lambda on it
 */
inline fun <reified T : Any> Context.intent(body: Intent.() -> Unit): Intent {
    val intent = Intent(this, T::class.java)
    intent.body()
    return intent
}

/**
 * Extension method to startActivity for Context.
 */

inline fun <reified T : Any> Context?.launchActivity() =
    this?.startActivity(Intent(this, T::class.java))

inline fun <reified T : Any> Context?.launchActivity(func: Intent.() -> Intent) =
    this?.startActivity(Intent(this, T::class.java).func())

inline fun <reified T : Any> AppCompatActivity.launchActivity(
    func: Intent.() -> Intent,
    requestCode: Int = -1
) =
    startActivityForResult(Intent(this, T::class.java).func(), requestCode)

/**
 * Extension method to show toast for Context.
 */
fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) =
    this?.let { Toast.makeText(it, text, duration).show() }

/**
 * Extension method to show toast for Context.
 */
fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) =
    this?.let { Toast.makeText(it, textId, duration).show() }

/**
 * Extension method to Get Color for resource for Context.
 */
fun Context.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(this, id)

/**
 * Extension method to Get Drawable for resource for Context.
 */
fun Context.getDrawableRes(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

/**
 * InflateLayout
 */
fun Context.inflateLayout(
    @LayoutRes layoutId: Int, parent: ViewGroup? = null,
    attachToRoot: Boolean = false
): View = LayoutInflater.from(this).inflate(layoutId, parent, attachToRoot)

/**
 * Extension method to get connectivityManager for Context.
 */
inline val Context.connectivityManager: ConnectivityManager?
    get() = getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager

/**
 * Extension method to get inputManager for Context.
 */
inline val Context.inputManager: InputMethodManager?
    get() = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager

/**
 * Extension method to make call for Context.
 */
fun Context.makeCall(number: String): Boolean {
    try {
        val intent = Intent(ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        return true
    } catch (e: Exception) {
        return false
    }
}

/**
 * Extension method to Send SMS for Context.
 */
fun Context.sendSms(number: String, text: String = ""): Boolean {
    try {
        val intent = Intent(ACTION_VIEW, Uri.parse("sms:$number")).apply {
            putExtra("sms_body", text)
        }
        startActivity(intent)
        return true
    } catch (e: Exception) {
        return false
    }
}

/**
 * Extension method to send sms for Context.
 */
fun Context.sms(phone: String?, body: String = "") {
    val smsToUri = Uri.parse("smsto:" + phone)
    val intent = Intent(Intent.ACTION_SENDTO, smsToUri)
    intent.putExtra("sms_body", body)
    startActivity(intent)
}

/**
 * Extension method to dail telephone number for Context.
 */
fun Context.dial(tel: String?) = startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel)))

/**
 * Extension method to browse for Context.
 */
fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    try {
        val intent = Intent(ACTION_VIEW).apply {
            data = Uri.parse(url)
            if (newTask) addFlags(FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        return true
    } catch (e: Exception) {
        return false
    }
}

/**
 * Extension method to rate app on PlayStore for Context.
 */
fun Context.rate(): Boolean =
    browse("market://details?id=$packageName") or browse("http://play.google.com/store/apps/details?id=$packageName")

/**
 * Extension method to provide quicker access to the [LayoutInflater] from [Context].
 */
fun Context.getLayoutInflater() =
    getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

/*
 * Check network connection
 */
fun Context.hasNetworkConnection(): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

/*
 * Set statusbar color
 */
fun Context.setSatatusBarColor(context: WeakReference<Activity>, @ColorRes colorResId: Int) {
    if (Build.VERSION.SDK_INT >= 21) {
        val window = context.get()?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = context?.get()!!.resources.getColor(colorResId)
    }
}