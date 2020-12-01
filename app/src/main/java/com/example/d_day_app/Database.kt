package com.example.d_day_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recyclerview_item::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun dao(): DatabaseDao

    companion object {
        @Volatile
        var INSTANCE: com.example.d_day_app.Database? = null
        fun getInstance(context: Context): com.example.d_day_app.Database {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        com.example.d_day_app.Database::class.java,
                        "userDatabase.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}