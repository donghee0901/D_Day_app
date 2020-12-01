package com.example.d_day_app

import androidx.room.*

@Dao
interface DatabaseDao{
    @Insert fun insert(item : Recyclerview_item)
    @Delete fun delete(item : Recyclerview_item)
    @Update fun update(item : Recyclerview_item)

    @Query("SELECT * from userdata")
    fun getAllData() : List<Recyclerview_item>

    @Query("DELETE from userdata")
    fun deleteAllData()
}