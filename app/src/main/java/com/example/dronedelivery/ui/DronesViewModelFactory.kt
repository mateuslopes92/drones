package com.example.dronedelivery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dronedelivery.data.Repository

class DronesViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    @Suppress( "UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DronesViewModel(repository) as T
    }
}