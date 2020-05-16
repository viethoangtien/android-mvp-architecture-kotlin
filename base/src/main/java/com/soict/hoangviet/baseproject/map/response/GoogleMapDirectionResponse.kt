package com.example.anothertimdatxe.map.response

import com.example.anothertimdatxe.map.entity.GeocodedWaypointsItem
import com.example.anothertimdatxe.map.entity.RoutesItem
import com.google.gson.annotations.SerializedName

data class GoogleMapDirectionResponse(

    @field:SerializedName("routes")
    val routes: List<RoutesItem?>? = null,

    @field:SerializedName("geocoded_waypoints")
    val geocodedWaypoints: List<GeocodedWaypointsItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)