package com.example.quotes

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.Adaptor.CategoryAdapter
import com.example.quotes.databinding.ActivityMainBinding
import com.example.quotes.model.CategoryModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        db.collection("Quotes").addSnapshotListener { value, error ->

            val list = arrayListOf<CategoryModel>()
            val data = value?.toObjects(CategoryModel::class.java)
            list.addAll(data!!)

            binding.category.layoutManager = LinearLayoutManager(this)
            binding.category.adapter = CategoryAdapter(this,list)
        }


        binding.btnMenu.setOnClickListener{
            if (binding.drawerMenu.isDrawerOpen(Gravity.LEFT)){
                binding.drawerMenu.closeDrawer(Gravity.LEFT)
            } else {
                binding.drawerMenu.openDrawer(Gravity.LEFT)
            }
        }



        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.share->{
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.android.example"))
                    startActivity(intent)
                    true
                }
                R.id.signOut->{
                    FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this, signinActivity::class.java)
                        startActivity(intent)
                    true
                }
                R.id.more->{
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                    }
                    true
                }
                R.id.rate->{
                    val appPackageName = "your.package.name"
                    try {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=$appPackageName")
                            )
                        )
                    } catch (anfe: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=$appPackageName")
                            )
                        )
                    }
                    true
                } else -> false

            }
        }
    }


    override fun onBackPressed() {
        if (binding.drawerMenu.isDrawerOpen(Gravity.LEFT)){
            binding.drawerMenu.closeDrawer(Gravity.LEFT)
        } else {
            super.onBackPressed()
        }
    }
}