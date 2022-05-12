package com.example.shop.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shop.R


class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemShortClickListener: ((ShopItem) -> Unit)? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return when (viewType) {
            ACTIVE -> {
                ShopItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_shop_enabled,
                        parent,
                        false
                    )
                )
            }
            NOT_ACTIVE -> {
                ShopItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_shop_disabled,
                        parent,
                        false
                    )
                )
            }
            else -> throw Exception("$viewType")
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.name.text = shopItem.name
        holder.count.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemShortClickListener?.invoke(shopItem)

        }


    }

    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        R.layout.item_shop_disabled
    }


    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (shopList[position].enabled) {
            true -> ACTIVE
            false -> NOT_ACTIVE
        }

//        if (shopList[position].enabled){
//            return ACTIVE
//        } else if (shopList[position].enabled) {
//            return NOT_ACTIVE
//        } else {
//
//        }
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val count: TextView = view.findViewById(R.id.count)

    }

    companion object {
        const val ACTIVE = 101
        const val NOT_ACTIVE = 100

        const val MAX_POOL_SIZE = 15
    }
}
