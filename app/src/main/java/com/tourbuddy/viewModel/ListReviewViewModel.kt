package com.tourbuddy.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tourbuddy.api.ApiConfig
import com.tourbuddy.api.DestinationResponse
import com.tourbuddy.api.ListReviewResponse
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListReviewViewModel(val token : String, val scope : CoroutineScope) : ViewModel() {
    private val _review = MutableLiveData<ListReviewResponse>()
    val review: LiveData<ListReviewResponse> = _review

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllReview("2")
    }

    //opsi 1 menggunakan callback
    fun getAllReview(id: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).getAllReview(id)
        client.enqueue(object : Callback<ListReviewResponse> {
            override fun onResponse(
                call: Call<ListReviewResponse>,
                response: Response<ListReviewResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _review.value = response.body()
                    Log.d("TAG", "onResponse: success")
                } else {
                    Log.d("TAG", "onResponse: failed")
                }
            }

            override fun onFailure(call: Call<ListReviewResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("TAG", "onResponse: error")
            }

        })
    }
}