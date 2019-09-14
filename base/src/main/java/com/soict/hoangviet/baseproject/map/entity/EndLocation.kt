package com.example.anothertimdatxe.map.entity

import com.google.gson.annotations.SerializedName

data class EndLocation(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)