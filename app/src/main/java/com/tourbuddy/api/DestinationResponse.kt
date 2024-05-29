package com.tourbuddy.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DestinationResponse(

	@field:SerializedName("list_destination")
	val destinationResponse: ArrayList<DestinationResponseItem>
)

@Parcelize
data class DestinationResponseItem(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("destination_id")
	val destinationId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("maps-url")
	val mapsUrl: String? = null,

	@field:SerializedName("rating_count")
	val reviewCount: String? = null
) : Parcelable
