package com.example.moneyapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.moneyapp.R
import com.example.moneyapp.db.AppDatabase
import com.example.moneyapp.db.DataDao

class TransformActivity : AppCompatActivity() {

    private lateinit var dataDao: DataDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform)

        setupDatabase()
        if(intent.getStringExtra("button")=="add")
            loadToDataAddMenu()
        else loadToDataMinusMenu()
    }

    private fun setupDatabase(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,"database-moneyApp"
        ).allowMainThreadQueries().build()
        dataDao = db.dataDao()
    }

    private fun loadToDataAddMenu(){

    }

    private fun loadToDataMinusMenu(){
        //TODO
    }
}