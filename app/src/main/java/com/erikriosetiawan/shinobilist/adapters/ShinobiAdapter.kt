package com.erikriosetiawan.shinobilist.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.shinobilist.databinding.ListShinobiBinding
import com.erikriosetiawan.shinobilist.models.Shinobi
import com.erikriosetiawan.shinobilist.views.DetailsActivity

class ShinobiAdapter(var context: Context, var shinobis: MutableList<Shinobi>) :
    RecyclerView.Adapter<ShinobiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ListShinobiBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shinobis[position]) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.DATA_SHINOBI_KEY, DetailsActivity.DATA_SHINOBI_KEY)
            intent.putExtra(DetailsActivity.DATA_SHINOBI_KEY, shinobis[position])
            context.startActivity(intent)
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