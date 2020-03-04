package com.soict.hoangviet.baseproject.extension

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Extension method to display toast text for Fragment.
 */
fun Fragment?.toast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = this?.let { activity.toast(text, duration) }

/**
 * Extension method to display toast text for Fragment.
 */
fun Fragment?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { activity.toast(textId, duration) }

/**
 * Extension method to make call for Fragment.
 */
fun Fragment.makeCall(number: String) = requireActivity().makeCall(number)

/**
 * Extension method to send sms for Fragment.
 */
fun Fragment.sendSms(number: String, text: String = "") = requireActivity().sendSms(number, text)

/**
 * Extension method to rate in playstore for Fragment.
 */
fun Fragment.rate() = requireActivity().rate()

/**
 * Extension method to provide hide keyboard for [Fragment].
 */
fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}
