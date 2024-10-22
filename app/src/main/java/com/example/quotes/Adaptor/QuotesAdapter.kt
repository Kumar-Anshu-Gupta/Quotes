package com.example.quotes.Adaptor

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.AllQuotes
import com.example.quotes.R
import com.example.quotes.databinding.ItemQuotesBinding
import com.example.quotes.model.QuotesModel



class QuotesAdapter(val allQuotes: AllQuotes, val QuotesList: ArrayList<QuotesModel>) :RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {
    class QuotesViewHolder(val binding: ItemQuotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder(
            ItemQuotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = QuotesList.size


    @SuppressLint("ServiceCast")
    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.binding.ItemQuotes.text=QuotesList[position].data.toString()

        if(position % 5 == 0){
            holder.binding.back.setBackgroundResource(R.drawable.gradient_1)
        } else if (position % 5 == 1){
            holder.binding.back.setBackgroundResource(R.drawable.gradient_1)
        } else if (position % 5 == 2){
            holder.binding.back.setBackgroundResource(R.drawable.gradient_1)
        }else if (position % 5 == 3){
            holder.binding.back.setBackgroundResource(R.drawable.gradient_1)
        }else {
            holder.binding.back.setBackgroundResource(R.drawable.gradient_1)
        }

        holder.binding.btnShare.setOnClickListener{
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n${QuotesList[position].data}\n\n"
                shareMessage =
                    shareMessage + "https://play.google.com/store/apps/details?id=" + allQuotes.intent + "\n\n"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allQuotes.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }

holder.binding.copy.setOnClickListener{
    val clipboardManager = holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text",QuotesList[position].data.toString() )
    clipboardManager.setPrimaryClip(clipData)
    Toast.makeText(holder.itemView.context, "Text copied to clipboard", Toast.LENGTH_LONG).show()
}
        holder.binding.whatsapp.setOnClickListener{
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.setType("text/plain")
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, QuotesList[position].data.toString())
            try {
                allQuotes.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {
            }
        }

    }


    fun String.copyToClipboard(context: Context,) {
        val clipBoard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("label",this)
        clipBoard.setPrimaryClip(clipData)
    }
}