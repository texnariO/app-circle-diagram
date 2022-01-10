package com.example.moneyapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "value") var value: Float?,
    @ColumnInfo(name = "category") val category: String
)
