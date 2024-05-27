package com.tourbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tourbuddy.data.Destination
import com.tourbuddy.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var rvDestination: RecyclerView
    private lateinit var listDestinationAdapter : ListDestinationAdapter
    private val list = ArrayList<Destination>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvDestination = binding.rvDestination
        rvDestination.setHasFixedSize(true)

        list.addAll(getDestinationList())
        showRecyclerlist()

        with(binding){
            searchBar.clearFocus()
            searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterList(newText.toString())
                    return true
                }
            })
        }

        binding.ivProfile.setOnClickListener {
            showMenu(it)
        }
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
        listDestinationAdapter = ListDestinationAdapter(list)
        rvDestination.adapter = listDestinationAdapter
    }

    private fun filterList(filter : String) {
        val filteredList = ArrayList<Destination>()
        for (item in list) {
            if (item.name.lowercase().contains(filter.lowercase()) ||
                item.location.lowercase().contains(filter.lowercase())) {
                filteredList.add(item)
            }
        }
        if (filteredList.isNotEmpty()) {
            listDestinationAdapter.setFilteredList(filteredList)
        }
    }

    fun showMenu(v: View) {
        PopupMenu(this, v).apply {
            setOnMenuItemClickListener(this@MainActivity)
            inflate(R.menu.item_menu)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> { // todo profile
                true
            }
            R.id.action_bookmark -> { // to do bookmark
                true
            }
            R.id.action_signout -> {
                startActivity(Intent(this@MainActivity, OnboardingActivity::class.java))
                finish()
                true
            }
            else -> false
        }
    }
}