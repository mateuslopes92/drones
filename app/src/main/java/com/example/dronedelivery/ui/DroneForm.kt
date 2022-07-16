package com.example.dronedelivery.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dronedelivery.MainActivity
import com.example.dronedelivery.R
import com.example.dronedelivery.data.Drone
import com.example.dronedelivery.data.Location
import com.example.dronedelivery.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_drone_form.*
import java.lang.StringBuilder

class DroneForm : AppCompatActivity() {
    private var locationList = mutableListOf<Location>()
    private var droneList = mutableListOf<Drone>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drone_form)
        initializeUi()
    }

    private fun initializeUi(){
        val factory = InjectorUtils.provideDronesViewModelFactory()
        val viewModel = ViewModelProvider(this, factory)
            .get(DronesViewModel::class.java)

        button_addLocation.setOnClickListener{
            if(
                editTextText_Location.text.toString().trim().isNotBlank()
                ||  editTextText_PackageWeight.text.toString().trim().isNotBlank()
            ) {
                val item = Location(
                    editTextText_Location.text.toString(),
                    editTextText_PackageWeight.text.toString()
                )
                locationList.add(item)
                viewModel.addLocation(item)
                editTextText_Location.setText("")
                editTextText_PackageWeight.setText("")
                getLocations()
            }
        }

        button_addDrone.setOnClickListener{
            if(
                editTextText_Name.text.toString().trim().isNotBlank()
                ||  editTextText_Weight.text.toString().trim().isNotBlank()
            ) {
                val item = Drone(
                    editTextText_Name.text.toString(),
                    editTextText_Weight.text.toString()
                )
                droneList.add(item)
                viewModel.addDrone(item)
                editTextText_Name.setText("")
                editTextText_Weight.setText("")
                getDrones()
            }
        }

        button_save.setOnClickListener{
            editTextText_Name.setText("")
            editTextText_Weight.setText("")
            editTextText_Location.setText("")
            editTextText_PackageWeight.setText("")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getLocations(){
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            locationList
        )

        listView_locations.adapter = arrayAdapter
    }

    private fun getDrones(){
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            droneList
        )

        listView_drones.adapter = arrayAdapter
    }

}