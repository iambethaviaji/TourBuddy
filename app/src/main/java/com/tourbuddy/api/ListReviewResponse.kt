package com.tourbuddy.api

import com.google.gson.annotations.SerializedName

data class ListReviewResponse(

	@field:SerializedName("list_review")
	val listReview: ArrayList<ListReviewItem>
)

data class ListReviewItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("review_id")
	val reviewId: String,

	@field:SerializedName("review")
	val review: String,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("destination_id")
	val destinationId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: String
)
