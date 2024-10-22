package com.example.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.Adaptor.QuotesAdapter
import com.example.quotes.databinding.ActivityAllQuotesBinding
import com.example.quotes.model.QuotesModel
import com.google.firebase.firestore.FirebaseFirestore

class AllQuotes : AppCompatActivity() {

    lateinit var binding: ActivityAllQuotesBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        db = FirebaseFirestore.getInstance()

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }
        binding.catName.text = name.toString()

        db.collection("Quotes").document(id!!).collection("all")
            .addSnapshotListener { value, error ->
                val QuotesList = arrayListOf<QuotesModel>()
                val data = value?.toObjects(QuotesModel::class.java)
                QuotesList.addAll(data!!)

                binding.rcvALlQuotes.layoutManager = LinearLayoutManager(this)
                binding.rcvALlQuotes.adapter= QuotesAdapter(this, QuotesList)
            }



    }
}