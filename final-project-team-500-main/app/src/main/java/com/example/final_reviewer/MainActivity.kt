package com.example.final_reviewer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val TEAM_500 = "TEAM_500_DEBUG"
const val BASE_URL = "https://api.yelp.com/v3/"
private fun Unit.enqueue(callback: Callback<Any>) {

}

class MainActivity : AppCompatActivity() {
    private lateinit var restaurantNameET: EditText
    private lateinit var restaurantCityAddressET: EditText
    private lateinit var searchBTN: Button
    private lateinit var myReviewsBTN: Button
    private lateinit var restaurantName: String
    private lateinit var restaurantAddress: String
    private lateinit var recyclerViewSearch: RecyclerView
    val restaurantsList = mutableListOf<YelpRestaurant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ensures that initially starting the app, the home screen will be the first thing we see
        //replaceFragment(HomeFragment())

        //calling the navigation bar function
        connectGUI()
        setSearchBtn()
        setMyReviewsBtn()
        linkSearchRecycler()


        setEditTextListener(restaurantNameET)
        setEditTextListener(restaurantCityAddressET)
    }

    private fun linkSearchRecycler() {
        recyclerViewSearch = findViewById(R.id.searchResults_recyclerView)
    }

    private fun populateSearch() {
        recyclerViewSearch.layoutManager = LinearLayoutManager(this)
        val adapter = Search_RecyclerViewAdapter(restaurantsList)
        recyclerViewSearch.adapter = adapter
    }

    private fun connectGUI() {
        restaurantNameET = findViewById(R.id.restaurantName_editText)
        restaurantCityAddressET = findViewById(R.id.restaurantAddress_editText)
        searchBTN = findViewById(R.id.search_button)
        myReviewsBTN = findViewById(R.id.myReviews_button)
    }

    private fun setMyReviewsBtn() {
        myReviewsBTN.setOnClickListener {
            //When pressed a new activity is shown with all previously reviewed restaurants
            val intent = Intent(this, PreviousReviews::class.java)
            startActivity(intent)
        }
    }

    private fun setSearchBtn() {
        //When pressed a api request is sent.
        searchBTN.setOnClickListener {
            restaurantName = restaurantNameET.text.toString()
            Log.i(TEAM_500, "Restaurant Name = $restaurantName")
            restaurantAddress = restaurantCityAddressET.text.toString()
            Log.i(TEAM_500, "Restaurant Address = $restaurantAddress")

            sendRequest(restaurantName, restaurantAddress)
        }
    }


    private fun sendRequest(name: String, address: String) {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val yelpParams = retrofit.create(YelpApi::class.java)
        yelpParams.yelpApiParams("Bearer ${getString(R.string.yelpKey)}", name, address)
            .enqueue(object : Callback<YelpApiData> {
                override fun onResponse(call: Call<YelpApiData>, response: Response<YelpApiData>) {
                    Log.i(TEAM_500, "onResponse $response[0]")
                    val body = response.body()
                    if (body == null) {
                        Log.e(TEAM_500, "ERROR - Response Body is Null")
                        return
                    } else {
                        restaurantsList.clear()
                        restaurantsList.addAll(body.restaurants)
                        debugRestaurantList()
                        populateSearch()
                    }
                }

                override fun onFailure(call: Call<YelpApiData>, t: Throwable) {
                    Log.e(TEAM_500, "onFailure $t")
                }
            })
    }

    //This function is used for debugging purposes to verify API response
    private fun debugRestaurantList() {
        for (i in restaurantsList) {
            Log.i(TEAM_500, "Restaurant Attributes = $i")
            Log.i(TEAM_500, "Restaurant Name = ${i.name}")
            Log.i(TEAM_500, "Restaurant imageUrl = ${i.imageUrl}")
            Log.i(TEAM_500, "Restaurant YelpUrl = ${i.url}")
            Log.i(TEAM_500, "Restaurant address1 = ${i.location.address1}")
            Log.i(TEAM_500, "Restaurant address2 = ${i.location.address2}")
            Log.i(TEAM_500, "Restaurant city = ${i.location.city}")
            Log.i(TEAM_500, "Restaurant zipcode = ${i.location.zipCode}")
            Log.i(TEAM_500, "Restaurant state = ${i.location.state}")
            Log.i(TEAM_500, "--------------------------------")
        }
    }

    private fun setEditTextListener(edittext: EditText) {
        edittext.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                edittext.clearFocus() // Clear focus to hide the keyboard
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    edittext.windowToken,
                    0
                )
                true
            } else {
                false
            }
        }
    }
}