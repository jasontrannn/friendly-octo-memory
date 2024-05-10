package com.example.final_reviewer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


val DB_Name = "RestaurantDB"
val Table_Name = "Restaurants"
val Col_Name = "restaurant_name"
val Col_Rating = "rating"
val Col_Comments = "comments"
val Col_Address = "address"
val Col_YelpUrl = "yelpUrl"
val Col_ImageUrl = "imageUrl"
val Col_ID = "id"


//Data base file location = View -> Tool Window -> Device Explorer (Select current device) -> data -> data -> com.example.final_reviewer -> databases -> RestaurantDB
class DBHelper(context: Context): SQLiteOpenHelper(context, DB_Name,null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //SQL query to create the table on the database file
        val createTableQuery = "CREATE TABLE " + Table_Name + " (" + Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_Name + " VARCHAR(256)," + Col_Rating + " VARCHAR(256)," + Col_Comments + " VARCHAR(256)," +
                Col_Address + " VARCHAR(256)," + Col_YelpUrl + " VARCHAR(256)," + Col_ImageUrl + " VARCHAR(256))"

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    //Add a new row into the DB
    fun insertData(restaurant: Restaurant) {
        val db = this.writableDatabase

        var restaurantCV = ContentValues()
        restaurantCV.put(Col_Name, restaurant.name)
        restaurantCV.put(Col_Rating, restaurant.rating)
        restaurantCV.put(Col_Comments, restaurant.comments)
        restaurantCV.put(Col_Address, restaurant.address)
        restaurantCV.put(Col_YelpUrl, restaurant.yelpUrl)
        restaurantCV.put(Col_ImageUrl, restaurant.imageUrl)

        var result = db.insert(Table_Name, null, restaurantCV)

        if(result == -1.toLong()) {
            Log.e("DBHelper", "Error - From DBHelper")
        }
        else{
            Log.i("DBHelper", "Success - From DBHelper")
        }
    }

    //Get the content inside the database
    fun readDataDB() : MutableList<Restaurant> {
        var listDB : MutableList<Restaurant> = ArrayList()

        val db = this.readableDatabase
        val sqlQuery = "Select * from $Table_Name"
        val cursor = db.rawQuery(sqlQuery, null)

        //If the cursor is not null
        if(cursor.moveToFirst()) {
            do {
                var restaurant = Restaurant()
                restaurant.id = cursor.getString(cursor.getColumnIndexOrThrow(Col_ID)).toInt()
                restaurant.name = cursor.getString(cursor.getColumnIndexOrThrow(Col_Name))
                restaurant.rating = cursor.getString(cursor.getColumnIndexOrThrow(Col_Rating))
                restaurant.rating = cursor.getString(cursor.getColumnIndexOrThrow(Col_Comments))
                restaurant.address = cursor.getString(cursor.getColumnIndexOrThrow(Col_Address))
                restaurant.yelpUrl = cursor.getString(cursor.getColumnIndexOrThrow(Col_YelpUrl))
                restaurant.imageUrl = cursor.getString(cursor.getColumnIndexOrThrow(Col_ImageUrl))

                listDB.add(restaurant)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return listDB
    }

    //fun deleteData(i : Int) {
    fun deleteData(i : String) {
        val DB = this.writableDatabase

        DB.delete(Table_Name, Col_Address + "= ?", arrayOf(i))
        Log.i("MY_LOG_TAG", "DBHelper - From deleteData")

        DB.close()
    }

//    fun editDataDB() {
//        val DB = this.writableDatabase
//        val sqlQuery = "Select * from $Table_Name"
//        val cursor = DB.rawQuery(sqlQuery, null)
//
//        //If the cursor is not null
//        if(cursor.moveToFirst()) {
//            do {
//                var CV = ContentValues()
//
//                CV.put()
//
//                DB.update(Table_Name, )
//
//            } while (cursor.moveToNext())
//        }
//
//        cursor.close()
//    }
}