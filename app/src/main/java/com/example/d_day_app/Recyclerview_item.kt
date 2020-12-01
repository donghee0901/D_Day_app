package com.example.d_day_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userdata")
data class Recyclerview_item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "content")
    var content : String
)