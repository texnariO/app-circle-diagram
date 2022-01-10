package com.example.moneyapp.db

import androidx.room.*
import com.example.moneyapp.models.Data


@Dao
interface DataDao {
    @Query("SELECT * FROM data")
    fun getAll(): List<Data>

    @Query("SELECT * FROM data WHERE category LIKE :categ")
    fun loadInfoAboutCategory(categ: String): Data

    @Query("SELECT category FROM data")
    fun loadAllCategory(): List<String>

    @Update
    fun updateData(data: Data)

    @Insert
    fun insertData(data :Data)

    @Delete
    fun delete(data: Data)
}