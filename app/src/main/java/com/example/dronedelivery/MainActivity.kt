package com.example.dronedelivery

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dronedelivery.data.Drone
import com.example.dronedelivery.data.Location
import com.example.dronedelivery.data.Trip
import com.example.dronedelivery.ui.DroneForm
import com.example.dronedelivery.ui.DronesViewModel
import com.example.dronedelivery.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_drone_form.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private var locationList = mutableListOf<Location>()
    private var droneList = mutableListOf<Drone>()
    private var tripList = mutableListOf<Trip>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUi()
    }

    private fun initializeUi() {
        val factory = InjectorUtils.provideDronesViewModelFactory()
        val viewModel = ViewModelProvider(this, factory)
            .get(DronesViewModel::class.java)

        var counter = 0

        viewModel.getDrones().observe(this, Observer { drones ->
            var locationsListed = mutableListOf<Location>()
            drones.forEachIndexed{indexDrone, drone ->
                var locationsSum = 0.0
                counter += 1
                viewModel.getLocations().observe(this, Observer { locations ->
                        locations.forEachIndexed { index, location ->
                        var droneMaxWeight = drone.droneWeight.toDouble()

                        if(locationsSum < droneMaxWeight){
                            locationsListed.add(location)
                            locationsSum += location.packageWeight.toDouble()
                        }
                    }
                })
                locationsSum = 0.0
                tripList.add(Trip(
                    "Trip #${counter}",
                    drone.droneName,
                    indexDrone,
                    locationsListed
                ))
            }
            Log.e("TRIPS ==> ", tripList.toString())
            if(tripList.isNotEmpty()){
                val arrayAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    tripList
                )

                listView_Trips.adapter = arrayAdapter
                listView_Trips.visibility = ListView.VISIBLE
                imageView_drone. visibility = ImageView.GONE
                textView_Drones.visibility = TextView.GONE
            }
        })

        fab.setOnClickListener{
            val intent = Intent(this, DroneForm::class.java)
            startActivity(intent)
        }

    }

}