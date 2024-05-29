package com.tourbuddy.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tourbuddy.api.ApiConfig
import com.tourbuddy.api.ApiService
import com.tourbuddy.api.DestinationResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class DestinationViewModel(val token : String, val scope : CoroutineScope) : ViewModel() {
    private val _destination = MutableLiveData<DestinationResponse>()
    val destination : LiveData<DestinationResponse> = _destination

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    init {
        getAllDestination("Indonesia")
    }

    //opsi 1 menggunakan callback
    fun getAllDestination(city : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).getAllDestinations(city)
        client.enqueue(object : Callback<DestinationResponse> {
            override fun onResponse(
                call: Call<DestinationResponse>,
                response: Response<DestinationResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _destination.value = response.body()
                    Log.d("TAG", "onResponse: success")
                } else {
                    Log.d("TAG", "onResponse: failed")
                }
            }

            override fun onFailure(call: Call<DestinationResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("TAG", "onResponse: error")
            }

        })
    }

    //opsi 2 menggunakan suspend function
//    fun getAllDestination(city : String) {
//        _isLoading.value = true
//        scope.launch {
//            try {
//                _destination.postValue(ApiConfig.getApiService(token).getAllDestinations(city))
//                _isLoading.postValue(false)
//                Log.d("TAG", "getAllDestination: success")
//            } catch (e : HttpException) {
//                val jsonInString = e.response()?.errorBody()?.string()
//                val errorBody = Gson().fromJson(jsonInString, DestinationResponse::class.java)
//                Log.d("TAG", "getAllDestination: $errorBody")
//                _isLoading.postValue(false)
//            }
//        }
//    }
}