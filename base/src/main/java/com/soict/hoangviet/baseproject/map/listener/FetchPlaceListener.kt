package com.soict.hoangviet.baseproject.map.listener

import com.google.android.gms.maps.model.LatLng

interface FetchPlaceListener {
    fun onSuccessFetchPlaces(mLatLng: LatLng)
    fun onErrorFetchPlaces()
}