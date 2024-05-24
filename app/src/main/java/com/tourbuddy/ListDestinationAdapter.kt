package com.tourbuddy

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tourbuddy.data.Destination


class ListDestinationAdapter(private val listDestination: ArrayList<Destination>): RecyclerView.Adapter<ListDestinationAdapter.ListViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.destination_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listDestination.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, location, photo) = listDestination[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvlocation.text = location

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_destination", listDestination[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvlocation: TextView = itemView.findViewById(R.id.tv_location)
    }
}