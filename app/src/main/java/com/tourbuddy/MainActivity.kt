package com.tourbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tourbuddy.data.Destination

class MainActivity : AppCompatActivity() {
    private lateinit var rvDestination: RecyclerView
    private val list = ArrayList<Destination>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDestination = findViewById(R.id.rv_destination)
        rvDestination.setHasFixedSize(true)

        list.addAll(getDestinationList())
        showRecyclerlist()
    }

    private fun getDestinationList(): ArrayList<Destination>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listDestination = arrayListOf<Destination>()
        for (i in dataName.indices) {
            val destination = Destination(dataName[i], dataLocation[i], dataPhoto.getResourceId(i, -1))
            listDestination.add(destination)
        }
        return listDestination
    }

    private fun showRecyclerlist() {
        rvDestination.layoutManager = LinearLayoutManager(this)
        val listDestinationAdapter = ListDestinationAdapter(list)
        rvDestination.adapter = listDestinationAdapter
    }
}