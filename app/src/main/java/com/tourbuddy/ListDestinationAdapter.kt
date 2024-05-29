package com.tourbuddy

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tourbuddy.api.DestinationResponseItem
import com.tourbuddy.data.Destination
import org.w3c.dom.Text

class ListDestinationAdapter(private var listDestination: ArrayList<DestinationResponseItem>): RecyclerView.Adapter<ListDestinationAdapter.ListViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.destination_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listDestination.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (city, photo, id, name, rating, description, location, reviewCount) = listDestination[position]

        holder.tvName.text = name
        holder.tvlocation.text = city
        holder.tvRating.text = rating
        holder.tvReviewCount.text = holder.itemView.context.getString(R.string.review_count, reviewCount)
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_destination", listDestination[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    fun setFilteredList(filteredDestination : ArrayList<DestinationResponseItem>) {
        listDestination = filteredDestination
        notifyDataSetChanged()

    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvlocation: TextView = itemView.findViewById(R.id.tv_location)
        val tvRating : TextView = itemView.findViewById(R.id.tv_rating)
        val tvReviewCount : TextView = itemView.findViewById(R.id.tv_review_count)
    }
}