package com.example.firebaseapplication.Adapter

import  android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebaseapplication.DataModal.EmpData
import com.example.firebaseapplication.MainActivity2
import com.example.firebaseapplication.R
import com.example.firebaseapplication.ViewHolder.ViewHolder

class EmpAdapter (val context: Context, val eData:ArrayList<EmpData>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_adapter,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context).load(eData[position].Images1).into(holder.itemView.findViewById(R.id.imagesnew))



        holder.itemView.findViewById<CardView>(R.id.cardelivation).setOnClickListener {
            val intent=Intent(context, MainActivity2::class.java)
            intent.putExtra("catId",eData[position].categoryId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int =eData.size
}

