package com.example.encoderdecoderapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdap(val entries: ArrayList<Phrase>) : RecyclerView.Adapter<RVAdap.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false
            )
        )

    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val entry = entries[position]

        holder.itemView.apply {
            tvItem.text = entry.paras
            if(!entry.original){tvItem.setTextColor(Color.BLUE)}

        }
        }


    override fun getItemCount(): Int = entries.size

}