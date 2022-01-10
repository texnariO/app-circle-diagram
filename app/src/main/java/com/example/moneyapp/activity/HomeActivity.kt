package com.example.moneyapp.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.moneyapp.R
import com.example.moneyapp.db.AppDatabase
import com.example.moneyapp.db.DataDao
import com.example.moneyapp.util.Colors
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

class HomeActivity : AppCompatActivity() {

    private lateinit var pieChart: PieChart
    private lateinit var buttonAdd: Button
    private lateinit var buttonMinus: Button
    private lateinit var dataDao: DataDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        pieChart = findViewById(R.id.circleDiagram)
        setupDatabase()
        setupPieChart()
        loadDataToPieChart()
        buttonAdd = findViewById(R.id.ButtonAdd)
        buttonAdd.setOnClickListener {
            val intent = Intent(this,TransformActivity::class.java)
            intent.putExtra("button","add")
            startActivity(intent)
        }

        buttonMinus = findViewById(R.id.ButtonMinus)
        buttonMinus.setOnClickListener{
            val intent = Intent(this,TransformActivity::class.java)
            intent.putExtra("button","min")
            startActivity(intent)
        }
    }

    private fun setupDatabase(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,"database-moneyApp"
        ).allowMainThreadQueries().build()
        dataDao = db.dataDao()
    }

    private  fun setupPieChart(){
        pieChart.isDrawHoleEnabled = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setEntryLabelTextSize(12F)
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false
    }

    private fun loadDataToPieChart(){
        //TODO load data from DB
        val dataEntries = dataDao.getAll()
        val entries = ArrayList<PieEntry>()
        for (data in dataEntries){
            entries.add(PieEntry(data.value!!,data.category))
        }
/*        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(0.2f,"Food"))
        entries.add(PieEntry(0.15f,"Medical"))
        entries.add(PieEntry(0.10f,"Shop"))
        entries.add(PieEntry(0.25f,"Home"))
        entries.add(PieEntry(0.28f,"IT"))*/

        val colors = ArrayList<Int>()
        for (color in Colors){
            colors.add(color)
        }

        var dataSet = PieDataSet(entries,"Category")
        dataSet.colors = colors;

        var data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)

        pieChart.data  = data
        pieChart.invalidate()
    }

}




