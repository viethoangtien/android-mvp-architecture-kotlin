package com.soict.hoangviet.baseproject.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import java.lang.Exception
import java.util.*

object MapUtil {
    val TAG = MapUtil::class.java.simpleName
    const val KEY_MAP_DESTINATION = "Destination"
    const val KEY_MAP_CURRENT = "Current"
    const val KEY_MAP_SEARCH_ORIGIN = "origin"
    const val KEY_MAP_SEARCH_DESTINATION = "destination"
    const val KEY_MAP_COUNTRY = "VN"
    const val KEY_MAP_UPPER_LEFT_CORNER = -33.880490
    const val KEY_MAP_LOWER_LEFT_CORNER = -33.858754
    const val KEY_MAP_UPPER_RIGHT_CORNER = 151.184363
    const val KEY_MAP_LOWER_RIGHT_CORNER = 151.229596

    fun getGeocoder(
        context: Context,
        latitude: Double,
        longitude: Double,
        maxResult: Int
    ): MutableList<Address> {
        val mGeocoder = Geocoder(context, Locale.getDefault())
        return mGeocoder.getFromLocation(latitude, longitude, maxResult)
    }

    fun getAddressByLatLng(
        context: Context,
        latitude: Double,
        longitude: Double,
        maxResult: Int
    ): String {
        try {
            return getGeocoder(context, latitude, longitude, maxResult).get(0).getAddressLine(0)
        } catch (e: Exception) {
            Log.w(TAG, "Canont get Address!")
        }
        return ""
    }

    fun getCityByLatLng(
        context: Context,
        latitude: Double,
        longitude: Double,
        maxResult: Int
    ): String {
        try {
            return getGeocoder(context, latitude, longitude, maxResult).get(0).locality
        } catch (e: Exception) {
            Log.w(TAG, "Canont get Address!")
        }
        return ""
    }

    fun getStateByLatLng(
        context: Context,
        latitude: Double,
        longitude: Double,
        maxResult: Int
    ): String {
        try {
            return getGeocoder(context, latitude, longitude, maxResult).get(0).adminArea
        } catch (e: Exception) {
            Log.w(TAG, "Canont get Address!")
    }
        return ""
    }

    fun getCountryByLatLng(
        context: Context,
        latitude: Double,
        longitude: Double,
        maxResult: Int
    ): String {
        try {
            return getGeocoder(context, latitude, longitude, maxResult).get(0).countryName
        } catch (e: Exception) {
            Log.w(TAG, "Canont get Address!")
        }
        return ""
    }

    fun getPostalCodeByLatLng(
        context: Context,
        latitude: Double,
        longitude: Double,
        maxResult: Int
    ): String {
        try {
            return getGeocoder(context, latitude, longitude, maxResult).get(0).postalCode
        } catch (e: Exception) {
            Log.w(TAG, "Canont get Address!")
        }
        return ""
    }


}