package com.tourbuddy

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tourbuddy.data.Review

class ListReviewAdapter(private val listReview: ArrayList<Review>) : RecyclerView.Adapter<ListReviewAdapter.ListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.review_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listReview.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, review, rating, photo) = listReview[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvReview.text = review

        val filledStarResId = R.drawable.star_enabled
        when(rating){
            5 -> {
                holder.ivStar1.setImageResource(filledStarResId)
                holder.ivStar2.setImageResource(filledStarResId)
                holder.ivStar3.setImageResource(filledStarResId)
                holder.ivStar4.setImageResource(filledStarResId)
                holder.ivStar5.setImageResource(filledStarResId)
            }
            4 -> {
                holder.ivStar1.setImageResource(filledStarResId)
                holder.ivStar2.setImageResource(filledStarResId)
                holder.ivStar3.setImageResource(filledStarResId)
                holder.ivStar4.setImageResource(filledStarResId)
            }
            3 -> {
                holder.ivStar1.setImageResource(filledStarResId)
                holder.ivStar2.setImageResource(filledStarResId)
                holder.ivStar3.setImageResource(filledStarResId)
            }
            2 -> {
                holder.ivStar1.setImageResource(filledStarResId)
                holder.ivStar2.setImageResource(filledStarResId)
            }
            1 -> {
                holder.ivStar1.setImageResource(filledStarResId)
            }
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvReview: TextView = itemView.findViewById(R.id.tv_review)
        val ivStar1: ImageView = itemView.findViewById(R.id.star1)
        val ivStar2: ImageView = itemView.findViewById(R.id.star2)
        val ivStar3: ImageView = itemView.findViewById(R.id.star3)
        val ivStar4: ImageView = itemView.findViewById(R.id.star4)
        val ivStar5: ImageView = itemView.findViewById(R.id.star5)
    }
}