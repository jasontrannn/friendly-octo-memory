package com.example.final_reviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Search_RecyclerViewAdapter(val searchList: MutableList<YelpRestaurant>) : RecyclerView.Adapter<Search_RecyclerViewAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_recyclerview_row, parent, false)

        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val viewModelPosition = searchList[position]
        holder.prevRestaurantNameTV.text = viewModelPosition.name
        holder.prevRestaurantAddressTV.text = viewModelPosition.location.address1

        Picasso.get().load(viewModelPosition.imageUrl).fit().into(holder.prevRestaurantPictureTV)
    }


    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prevRestaurantNameTV: TextView = itemView.findViewById(R.id.name_textView)
        val prevRestaurantAddressTV: TextView = itemView.findViewById(R.id.address_textView)
        val prevRestaurantPictureTV: ImageView = itemView.findViewById(R.id.urlPicture)
    }

}
