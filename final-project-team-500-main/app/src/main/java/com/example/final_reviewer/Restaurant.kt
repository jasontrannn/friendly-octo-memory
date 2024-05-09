package com.example.final_reviewer

import android.media.Rating

public class Restaurant {
    var id : Int = 0
    var name: String = ""
    var address: String = ""
    var comments: String = ""
    var rating: String = ""
    var yelpUrl: String = ""
    var imageUrl: String = ""
    //val rating: Double,
    //val eatAgain: Boolean

    constructor(id: Int, name: String, address: String, comments: String, rating: String, yelpUrl: String, imageUrl: String) {
        this.id
        this.name = name
        this.address = address
        this.comments = comments
        this.rating = rating
        this.yelpUrl = yelpUrl
        this.imageUrl = imageUrl
    }

    constructor() {}

    public fun getRestaurantID() : Int { return id }
    public fun getRestaurantName() : String { return name }
    public fun getRestaurantAddress() : String { return address }
    public fun getRestaurantComments() : String { return comments }
    public fun getRestaurantRating() : String { return rating }
    public fun getRestaurantYelpUrl() : String { return yelpUrl }
    public fun getRestaurantImageUrl() : String { return imageUrl }
}
