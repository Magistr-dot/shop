package com.example.shop.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shoplist = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_enabled,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shoplist[position]
        val status = if (shopItem.enabled) {
            "Active"
        } else {
            "Not active"
        }
        if (shopItem.enabled) {
            holder.name.text = "${shopItem.name} + $status"
            holder.count.text = shopItem.count.toString()
        }
        holder.view.setOnLongClickListener {
            true
        }

    }
    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.name.text = ""
        holder.count.text = ""
    }


    override fun getItemCount(): Int {
        return shoplist.size
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val count: TextView = view.findViewById(R.id.count)

    }
}
