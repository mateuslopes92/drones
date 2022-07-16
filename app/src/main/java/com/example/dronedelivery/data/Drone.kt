package com.example.dronedelivery.data

data class Drone(val droneName: String, val droneWeight: String ) {
    override fun toString(): String {
        return "Drone: $droneName - Max weight: $droneWeight"
    }
}