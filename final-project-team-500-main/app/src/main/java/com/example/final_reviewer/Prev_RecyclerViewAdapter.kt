package com.example.final_reviewer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

//This Class is an adapter for the activity_Previous_Reviews.xml recyclerView. The parameter is a list of Restaurants from the DB.
class Prev_RecyclerViewAdapter(val prevList: List<Restaurant>) : RecyclerView.Adapter<Prev_RecyclerViewAdapter.PrevViewHolder>() {
    private var prev1 = PreviousReviews()
    //var recyclerViewInterface : Prev_RecyclerViewInterface()
    var onItemClick : ((Restaurant) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Prev_RecyclerViewAdapter.PrevViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prev_recyclerview_row, parent, false)

        return PrevViewHolder(view)
    }

    //Add the current Restaurant name and address to their corresponding textView.
    override fun onBindViewHolder(holder: Prev_RecyclerViewAdapter.PrevViewHolder, position: Int) {
        val viewModelPosition = prevList[position]
        holder.prevRestaurantNameTV.text = viewModelPosition.name
        holder.prevRestaurantAddressTV.text = viewModelPosition.address

        holder.prevRestaurantDeleteBTN.setOnClickListener{
            prev1.prevDeleteRow(position)
            Log.i("MY_LOG_TAG", "FROM Prev_RecyclerViewAdapter CLASS - FUNC. onBindViewHolder - delete btn clicked")
        }

        //When item on the recyclerView is clicked
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(viewModelPosition)
        }
    }

    override fun getItemCount(): Int {
        return prevList.size
    }

    //Connect the GUI components
    class PrevViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prevRestaurantNameTV: TextView = itemView.findViewById(R.id.name_textView)
        val prevRestaurantAddressTV: TextView = itemView.findViewById(R.id.address_textView)
        val prevRestaurantDeleteBTN: Button = itemView.findViewById(R.id.prevDelete_button)
    }
}