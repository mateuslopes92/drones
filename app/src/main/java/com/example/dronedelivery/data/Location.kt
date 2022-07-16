package com.example.dronedelivery.data

data class Location(val locationName: String, val packageWeight: String){
    override fun toString(): String {
        return "Location: $locationName - Weight: $packageWeight"
    }
}
