package com.example.final_reviewer

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.io.path.CopyActionContext

class PreviousReviews : AppCompatActivity() {
    //List containing previously rated restaurants
    var prevArrayList :MutableList<Restaurant> = ArrayList()

    private lateinit var recyclerViewPrev: RecyclerView
    private val context = this
    private var db = DBHelper(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_previous_reviews)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fillDB(context)
        setPrevArrayList()
        recyclerViewPrev.layoutManager = LinearLayoutManager(this)
        val adapter = Prev_RecyclerViewAdapter(prevArrayList)
        recyclerViewPrev.adapter = adapter

        //Add code to show fragment when item is clicked.
        adapter.onItemClick = {
            Log.i("MY_LOG_TAG", "FROM PreviousReviews CLASS - FUNC. onCreate - Clicked an item")
        }
    }

    //This function is used to fill the data base with manually created data. This is used only for testing purposes.
    //This will be replaced once we can dynamically add Restaurant objects to the DB.
    private fun fillDB(context: Context) {

        recyclerViewPrev = findViewById(R.id.prevRecyclerVIew)

        var id: Int = 100
        var name: String = "Starbucks"
        var address: String = "23622 Rockfield Blvd, Lake Forest, 92630, CA"
        var comments: String = "Test Comment - fillDB"
        var rating: String = "3"
        var yelpUrl: String = "https:yelp-fillDB-test"
        var imageUrl: String = "https:image-fillDB-test"

        var id1: Int = 101
        var name1: String = "Panda Express"
        var address1: String = "26022 Marguerite Pkwy, Mission Viejo, CA 92692"
        var comments1: String = "Test Comment-fillDB1"
        var rating1: String = "3"
        var yelpUrl1: String = "https:yelp-fillDB-test1"
        var imageUrl1: String = "https:image-fillDB-test1"

        var restaurant = Restaurant(id, name, address, comments, rating, yelpUrl, imageUrl)
        var restaurant1 = Restaurant(id1, name1, address1, comments1, rating1, yelpUrl1, imageUrl1)

        db.insertData(restaurant)
        db.insertData(restaurant1)
    }

    //This function copies the db content into a arraylist.
    private fun setPrevArrayList() {
        prevArrayList = db.readDataDB()
    }

    public fun prevDeleteRow(i : Int) {
        //db.deleteData(i)
        Log.i("MY_LOG_TAG", "FROM PreviousReviews CLASS - FUNC. prevDeleteRow - i == $i")
    }
}