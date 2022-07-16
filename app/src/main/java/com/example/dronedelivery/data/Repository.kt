package com.example.dronedelivery.data

class Repository private constructor(private val dao: FakeDao) {
    fun addLocation(location: Location){
        dao.addLocation(location)
    }

    fun getLocations() = dao.getLocations()

    fun addDrone(drone: Drone){
        dao.addDrone(drone)
    }

    fun getDrones() = dao.getDrones()

    companion object {
        @Volatile private var instance: Repository? = null

        fun getInstance(dao: FakeDao) =
            instance ?: synchronized(this){
                instance ?: Repository(dao).also { instance = it }
            }
    }
}