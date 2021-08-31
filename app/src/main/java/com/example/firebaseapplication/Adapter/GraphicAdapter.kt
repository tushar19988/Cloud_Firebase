package com.example.firebaseapplication.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebaseapplication.DataModal.GraphicModal
import com.example.firebaseapplication.MainActivity2
import com.example.firebaseapplication.R
import com.example.firebaseapplication.ViewHolder.ViewHolder

class GraphicAdapter(val context:Context , val gmodal:ArrayList<GraphicModal>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_graphic,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context).load(gmodal[position].img).into(holder.itemView.findViewById(R.id.image))

        /*holder.itemView.findViewById<CardView>(R.id.crdn).setOnClickListener {
            val intent=Intent(context,MainActivity2::class.java)
            context.startActivity(intent)
            }*/

    }

    override fun getItemCount(): Int = gmodal.size
}