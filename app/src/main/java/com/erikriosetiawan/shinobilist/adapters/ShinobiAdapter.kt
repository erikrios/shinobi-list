package com.erikriosetiawan.shinobilist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.shinobilist.databinding.ListShinobiBinding
import com.erikriosetiawan.shinobilist.models.Shinobi

class ShinobiAdapter(var context: Context, var shinobis: MutableList<Shinobi>) :
    RecyclerView.Adapter<ShinobiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ListShinobiBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shinobis[position]) {
            Toast.makeText(context, shinobis[position].name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = shinobis.size

    inner class ViewHolder(var binding: ListShinobiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shinobi: Shinobi, clickListener: (Shinobi) -> Unit) {
            binding.textViewShinobiName.text = shinobi.name
            itemView.setOnClickListener { clickListener(shinobi) }
        }
    }
}