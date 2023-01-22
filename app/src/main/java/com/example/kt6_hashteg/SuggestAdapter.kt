package com.example.kt6_hashteg

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kt6_hashteg.databinding.ItemSuggestionBinding

class SuggestAdapter(private val list: ArrayList<String>, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<SuggestAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ItemSuggestionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    inner class MessageViewHolder(private val binding: ItemSuggestionBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(text: String) {
            binding.itemText.text = "#$text"
            itemView.setOnClickListener {
                clickListener(text)
            }

        }
    }
}