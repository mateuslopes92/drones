package com.example.dronedelivery.ui

import androidx.lifecycle.ViewModel
import com.example.dronedelivery.data.Drone
import com.example.dronedelivery.data.Location
import com.example.dronedelivery.data.Repository

class DronesViewModel(private val repository: Repository): ViewModel() {
    fun getDrones() = repository.getDrones()

    fun addDrone(drone: Drone) = repository.addDrone(drone)

    fun getLocations() = repository.getLocations()

    fun addLocation(location: Location) = repository.addLocation(location)

}