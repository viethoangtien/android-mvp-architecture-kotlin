package com.example.anothertimdatxe.map.entity

import com.google.gson.annotations.SerializedName

data class Duration(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)