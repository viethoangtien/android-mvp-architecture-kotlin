package com.example.anothertimdatxe.map.entity

class Route(
        val originLat: Double,
        val originLng: Double,
        val destinationLat: Double,
        val destinationLng: Double,
        val overviewPolyline: String,
        val steps: List<StepsItem?>,
        val distance: String,
        val time: String)