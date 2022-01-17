package com.example.moneyapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.moneyapp.R
import com.example.moneyapp.db.AppDatabase
import com.example.moneyapp.db.DataDao
import com.example.moneyapp.models.Data
import com.example.moneyapp.util.CategoryAll

class TransformActivity : AppCompatActivity() {

    private lateinit var dataDao: DataDao
    private lateinit var mSpinner: Spinner
    private lateinit var mText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform)
        mSpinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, CategoryAll)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mSpinner.adapter = adapter
        mText = findViewById(R.id.editTextValue)
        setupDatabase()
        val buttonAccept = findViewById<Button>(R.id.bAccept)
        buttonAccept.setOnClickListener{
            if(intent.getStringExtra("button")=="add")
                loadToDataAddMenu()
            else loadToDataMinusMenu()
        }
    }

    private fun setupDatabase(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,"database-moneyApp"
        ).allowMainThreadQueries().build()
        dataDao = db.dataDao()
    }

    private fun loadToDataAddMenu(){
        val selected = mSpinner.selectedItem.toString()
        val dataFromDao = dataDao.loadInfoAboutCategory(selected)
        if(dataFromDao != null){
            val newDataDao = Data(dataFromDao.uid, dataFromDao.value!! + mText.text.toString().toFloat(),dataFromDao.category)
            dataDao.updateData(newDataDao)
        }
        else {
            println("**************************")
            println(mSpinner.selectedItemPosition)
            val newDataDao = Data(value = mText.text.toString().toFloat(),category = mSpinner.selectedItem.toString(),uid = mSpinner.selectedItemPosition+1)
            dataDao.insertData(newDataDao)
        }
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loadToDataMinusMenu(){
        val selected = mSpinner.selectedItem.toString()
        val dataFromDao = dataDao.loadInfoAboutCategory(selected)
        if(dataFromDao != null){
            if(dataFromDao.value!! - mText.text.toString().toFloat() <= 0){
                dataDao.delete(dataFromDao)
            }
            else {
                val newDataDao = Data(
                    dataFromDao.uid,
                    dataFromDao.value!! - mText.text.toString().toFloat(),
                    dataFromDao.category
                )
                dataDao.updateData(newDataDao)
            }
        }
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        Toast.makeText(this,R.string.error_not_category,Toast.LENGTH_SHORT).show()
        finish()
    }
}