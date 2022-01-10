package com.example.moneyapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moneyapp.models.Data

@Database(entities = [Data::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dataDao(): DataDao

}