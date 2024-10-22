package com.example.quotes.Adaptor

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.AllQuotes
import com.example.quotes.MainActivity
import com.example.quotes.databinding.ItemCategoryBinding
import com.example.quotes.model.CategoryModel

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.catViewHolder>() {
    val coloList = arrayListOf<String>("#55efc4","#81ecec","#74b9ff","#a29bfe","#b2bec3")
    class catViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): catViewHolder {
        return  catViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: catViewHolder, position: Int) {

        if(position % 5 == 0){
            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(coloList[0]))
        } else if (position % 5 == 1){
            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(coloList[1]))
        } else if (position % 5 == 2){
            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(coloList[2]))
        }else if (position % 5 == 3){
            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(coloList[3]))
        }else if (position % 5 == 4){
            holder.binding.itemTxt.setBackgroundColor(android.graphics.Color.parseColor(coloList[4]))
        }

        holder.binding.itemTxt.text = list[position].name.toString()
        holder.binding.root.setOnClickListener{

            val intent = Intent(mainActivity,AllQuotes::class.java)
            intent.putExtra("id",list[position].id)
            intent.putExtra("name",list[position].name)
            mainActivity.startActivity(intent)
        }
    }
}