package com.tourbuddy.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val name: String,
    val review: String,
    val rating: Int,
    val photo: Int
) : Parcelable