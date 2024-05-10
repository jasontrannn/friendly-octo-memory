package com.example.final_reviewer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpApi {
    @GET("businesses/search")
    fun yelpApiParams(
        @Header("Authorization") authHeader: String,
        @Query("term") restaurantName: String,
        @Query("location") restaurantLocation: String) : Call<YelpApiData>
}