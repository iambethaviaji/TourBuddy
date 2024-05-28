package com.tourbuddy.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope

class DestinationViewModelFactory private constructor(private val mToken: String, private val mScope : CoroutineScope) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: DestinationViewModelFactory? = null
        @JvmStatic
        fun getInstance(token: String, scope : CoroutineScope): DestinationViewModelFactory {
            if (INSTANCE == null) {
                synchronized(DestinationViewModelFactory::class.java) {
                    INSTANCE = DestinationViewModelFactory(token, scope)
                }
            }
            return INSTANCE as DestinationViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DestinationViewModel::class.java)) {
            return DestinationViewModel(mToken, mScope) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}