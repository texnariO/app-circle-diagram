package com.example.moneyapp.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moneyapp.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class HomeActivity : AppCompatActivity() {

    private lateinit var pieChart: PieChart;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        pieChart = findViewById(R.id.circleDiagram)
        setupPieChart()
        loadDataToPieChart()
    }

    private  fun setupPieChart(){
        pieChart.isDrawHoleEnabled = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setEntryLabelTextSize(12F)

    }

    private fun loadDataToPieChart(){
        //TODO load data from DB
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(0.2f,"Food"))
        entries.add(PieEntry(0.15f,"Medical"))
        entries.add(PieEntry(0.10f,"Shop"))
        entries.add(PieEntry(0.25f,"Home"))
        entries.add(PieEntry(0.28f,"IT"))

        val colors = ArrayList<Int>()
        for (color in ColorTemplate.MATERIAL_COLORS){
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