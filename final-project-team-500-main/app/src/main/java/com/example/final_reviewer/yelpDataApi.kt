package com.example.final_reviewer

import com.google.gson.annotations.SerializedName

data class YelpApiData (
    @SerializedName("businesses") val restaurants: List<YelpRestaurant>
)

data class YelpRestaurant(
    //@SerializedName("name") val name: String
    val name: String,
    @SerializedName("image_url") val imageUrl:String,
    val url: String,
    //val location: List<YelpLocation>
    val location: YelpLocation
)

data class YelpLocation(
    val address1: String,
    val address2: String,
    val city: String,
    @SerializedName("zip_code") val zipCode:String,
    val state: String
)

