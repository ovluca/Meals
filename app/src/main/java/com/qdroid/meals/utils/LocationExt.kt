package com.qdroid.meals.utils

import com.google.android.gms.maps.model.LatLng
import java.util.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class LocationExt {
    fun getLocation(defaultLocation: LatLng): LatLng {
        val random = Random()

        // Convert radius from meters to degrees
        val radiusInDegrees = (5000 / 111000f).toDouble()
        val u = random.nextDouble()
        val v = random.nextDouble()
        val w = radiusInDegrees * sqrt(u)
        val t = 2 * Math.PI * v
        val x = w * cos(t)
        val y = w * sin(t)

        // Adjust the x-coordinate for the shrinking of the east-west distances
        val new_x = x / cos(Math.toRadians(defaultLocation.latitude))
        val foundLongitude = new_x + defaultLocation.longitude
        val foundLatitude = y + defaultLocation.latitude
        println("Longitude: $foundLongitude  Latitude: $foundLatitude")
        return LatLng(foundLatitude, foundLongitude)
    }

}
