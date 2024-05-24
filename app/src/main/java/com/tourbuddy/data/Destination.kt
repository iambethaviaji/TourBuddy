package com.tourbuddy.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val name: String,
    val location: String,
    val photo: Int
) : Parcelable