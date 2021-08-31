package com.example.firebaseapplication

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseapplication.Adapter.EmpAdapter
import com.example.firebaseapplication.Adapter.GraphicAdapter
import com.example.firebaseapplication.DataModal.EmpData
import com.example.firebaseapplication.DataModal.GraphicModal
import com.example.firebaseapplication.databinding.ActivityMain2Binding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    var gData=ArrayList<GraphicModal>()
   lateinit  var gAdapter:GraphicAdapter
   lateinit var binding: ActivityMain2Binding
   lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val catId=intent.getStringExtra("catId")

        database = FirebaseFirestore.getInstance()

        binding.LenovoPro.layoutManager = LinearLayoutManager(this)
        gAdapter = GraphicAdapter(this, gData)
        binding.LenovoPro.adapter = gAdapter
        val random= Random
        val rand=random.nextInt(6)


        /* database.collection("users")
             .document(catId.toString())
             .collection("Doc")
             .document(catId.toString())*/

        database.collection("users")
            .document(catId.toString())
            .collection("Doc")
            .whereGreaterThanOrEqualTo("index",rand)
            .orderBy("index").limit(6).get()
            .addOnSuccessListener(object :OnSuccessListener<QuerySnapshot>{
                override fun onSuccess(p0: QuerySnapshot?) {

                    database.collection("users")
                        .document(catId.toString())
                        .collection("Doc")
                        .whereGreaterThanOrEqualTo("index",rand)
                        .orderBy("index").limit(6).get()
                        .addOnSuccessListener(object :OnSuccessListener<QuerySnapshot>{
                            @SuppressLint("NotifyDataSetChanged")
                            override fun onSuccess(p0: QuerySnapshot?) {
                                for (snapshot in p0!!) {
                                    val model: GraphicModal
                                    model = snapshot.toObject(GraphicModal::class.java)
                                    model.categoryId1=snapshot.id
                                    gData.add(model)
                                }
                                    gAdapter.notifyDataSetChanged()
                            }
                        })
                }
            })









       /* database.collection("Doc")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }*/





}
}



