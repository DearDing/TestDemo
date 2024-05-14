package com.ding.kotlin.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ding.kotlin.R

class CardVPAdapter(private val mContext: Context) : RecyclerView.Adapter<CardVPAdapter.ItemHolder>() {

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mTvContent: TextView

        init {
            mTvContent = view.findViewById(R.id.tv_content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_card_layout, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.mTvContent.text = "position = $position"
        holder.mTvContent.setTextColor(Color.WHITE)
        holder.itemView.setBackgroundColor(getColor(position))
    }

    private fun getColor(pos: Int): Int {
        return when (pos) {
            0 -> Color.BLUE
            1 -> Color.RED
            2 -> Color.BLACK
            3 -> Color.GREEN
            4 -> Color.YELLOW
            else -> {
                Color.CYAN
            }
        }
    }
}