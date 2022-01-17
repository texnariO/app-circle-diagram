package com.example.moneyapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyapp.models.RecyclerItem




class Adapter(
    private val data: List<RecyclerItem>
    ) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(com.example.moneyapp.R.id.imageRec)
        val text = itemView.findViewById<TextView>(com.example.moneyapp.R.id.textRec)
        val progressBar = itemView.findViewById<ProgressBar>(com.example.moneyapp.R.id.progressRec)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(com.example.moneyapp.R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = data[position].value.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}