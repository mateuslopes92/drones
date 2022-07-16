package com.example.dronedelivery.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeDao {
    private val droneList = mutableListOf<Drone>()
    private val drones = MutableLiveData<List<Drone>>()
    private val locationList = mutableListOf<Location>()
    private val locations = MutableLiveData<List<Location>>()

    init {
        drones.value = droneList
        locations.value = locationList
    }

    fun addLocation(location: Location){
        locationList.add(location)
        locations.value = locationList
    }

    fun getLocations() = locations as LiveData<List<Location>>

    fun addDrone(drone: Drone){
        droneList.add(drone)
        drones.value = droneList
    }

    fun getDrones() = drones as LiveData<List<Drone>>
}