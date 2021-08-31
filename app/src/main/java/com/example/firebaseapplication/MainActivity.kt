package com.example.firebaseapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseapplication.Adapter.EmpAdapter
import com.example.firebaseapplication.DataModal.EmpData
import com.example.firebaseapplication.databinding.ActivityMainBinding
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity() {
     var empData=ArrayList<EmpData>()
   lateinit  var eAdapter: EmpAdapter
   lateinit var binding:ActivityMainBinding
   lateinit var database: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseFirestore.getInstance()

        binding.mainrec.layoutManager = LinearLayoutManager(this)
        eAdapter = EmpAdapter(this, empData)
        binding.mainrec.adapter = eAdapter

        database.collection("users").addSnapshotListener(object :EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                empData.clear()
              /*  val snapshot= value?.documents */
                for (snapshot in value!!) {
                    val model: EmpData
                    model = snapshot.toObject(EmpData::class.java)
                    model.categoryId=snapshot.id
                    empData.add(model)
                }
                eAdapter.notifyDataSetChanged()
            }

        })





    }
}

