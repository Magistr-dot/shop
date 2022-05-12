package com.example.shop.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R

class ShopItemViewHolder {
    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val count: TextView = view.findViewById(R.id.count)
    }
}