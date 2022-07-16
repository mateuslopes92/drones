package com.example.dronedelivery.data

data class Trip(val tripName: String, val droneName: String, val droneIndex: Int, val locations: List<Location>){
    override fun toString(): String {
        return "\n Drone #$droneIndex $droneName - \n $tripName - \n ${locations.toString().replace("[", "").replace("]", "")} \n\n"
    }
}
