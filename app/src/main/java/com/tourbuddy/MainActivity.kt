package com.tourbuddy

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tourbuddy.api.DestinationResponseItem
import com.tourbuddy.data.Destination
import com.tourbuddy.databinding.ActivityMainBinding
import com.tourbuddy.viewModel.DestinationViewModel
import com.tourbuddy.viewModel.DestinationViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var rvDestination: RecyclerView
    private lateinit var listDestinationAdapter : ListDestinationAdapter
    private lateinit var auth: FirebaseAuth
    private val list = ArrayList<DestinationResponseItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mendapatkan token
//        var idToken : String ?= null
//        val mUser = auth.currentUser
//        mUser!!.getIdToken(true)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    idToken  = task.result.token
//                }
//            }

        //retrofit untuk akses ke api (masih error)
//        val destinationViewModel = obtainViewModel("")
//
//        rvDestination = binding.rvDestination
//        rvDestination.setHasFixedSize(true)
//
//        destinationViewModel.destination.observe(this) {
//            list.addAll(it.destinationResponse)
//            showRecyclerlist()
//        }
//
//        destinationViewModel.isLoading.observe(this) {
//            showLoading(it)
//        }

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
        val filteredList = ArrayList<DestinationResponseItem>()
        for (item in list) {
            if (item.name.toString().lowercase().contains(filter.lowercase()) ||
                item.city.toString().lowercase().contains(filter.lowercase())) {
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
                auth.signOut()
                startActivity(Intent(this@MainActivity, OnboardingActivity::class.java))
                finish()
                true
            }
            else -> false
        }
    }

    private fun obtainViewModel(token : String) : DestinationViewModel {
        val factory = DestinationViewModelFactory.getInstance(token, CoroutineScope(Dispatchers.IO))
        return ViewModelProvider(this, factory).get(DestinationViewModel::class.java)
    }

    private fun showLoading(isLoading : Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}