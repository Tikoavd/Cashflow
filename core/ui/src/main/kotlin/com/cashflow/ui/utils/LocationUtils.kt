package com.cashflow.ui.utils

import android.location.Location

fun getDistance(
    longitude1: Double,
    latitude1: Double,
    longitude2: Double,
    latitude2: Double
): Double {
    val point1 = Location("")
    point1.latitude = latitude1
    point1.longitude = longitude1
    val point2 = Location("")
    point2.latitude = latitude2
    point2.longitude = longitude2
    return point1.distanceTo(point2).toDouble()
}
