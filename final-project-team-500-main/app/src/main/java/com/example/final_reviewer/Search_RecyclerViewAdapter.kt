package com.example.final_reviewer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Search_RecyclerViewAdapter(val searchList: MutableList<YelpRestaurant>) : RecyclerView.Adapter<Search_RecyclerViewAdapter.SearchViewHolder>() {

    var onIteamClick : ((YelpRestaurant) -> Unit)? = null
    var clickedPosition : Int = -1
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

        //Get the full address of the restaurant
        var address1 = viewModelPosition.location.address1
        var addressCity = viewModelPosition.location.city
        var addressZip = viewModelPosition.location.zipCode
        var addressSate = viewModelPosition.location.state

        //Concatenate the full address
        var addressFull = address1 + ", " + addressCity + ", " +
                addressZip + ", " + addressSate

        holder.prevRestaurantAddressTV.text = addressFull

        Picasso.get().load(viewModelPosition.imageUrl).fit().into(holder.prevRestaurantPictureTV)
        Log.i("MY_LOG_TAG", "FROM Search_RecyclerViewAdapter - onBindViewHolder - POSTITON == $position")
//        clickedPosition = position
//        Log.i("MY_LOG_TAG", "clickedPosition == $clickedPosition")

        holder.itemView.setOnClickListener{
            clickedPosition = position
            Log.i("MY_LOG_TAG", "clickedPosition == $clickedPosition")
            onIteamClick?.invoke(viewModelPosition)
        }
    }


    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prevRestaurantNameTV: TextView = itemView.findViewById(R.id.name_textView)
        val prevRestaurantAddressTV: TextView = itemView.findViewById(R.id.address_textView)
        val prevRestaurantPictureTV: ImageView = itemView.findViewById(R.id.urlPicture)
    }

    public fun getPosition() : Int {
        return clickedPosition
    }
}
