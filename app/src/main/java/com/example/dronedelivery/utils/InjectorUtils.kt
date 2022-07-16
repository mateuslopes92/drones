package com.example.dronedelivery.utils

import com.example.dronedelivery.data.Repository
import com.example.dronedelivery.data.FakeDatabase
import com.example.dronedelivery.ui.DronesViewModelFactory

object InjectorUtils {

    fun provideDronesViewModelFactory(): DronesViewModelFactory{
        val repository = Repository.getInstance(FakeDatabase.getInstance().dao)
        return DronesViewModelFactory(repository)
    }
}