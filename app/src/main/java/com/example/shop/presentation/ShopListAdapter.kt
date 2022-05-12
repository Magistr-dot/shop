package com.example.shop.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shop.R
import com.example.shop.domain.ShopItem


class ShopListAdapter :
    ListAdapter<ShopItem, ShopItemViewHolder.ShopItemViewHolder>(ShopItemDiff()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemShortClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopItemViewHolder.ShopItemViewHolder {
        return when (viewType) {
            ACTIVE -> {
                ShopItemViewHolder.ShopItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_shop_enabled,
                        parent,
                        false
                    )
                )
            }
            NOT_ACTIVE -> {
                ShopItemViewHolder.ShopItemViewHolder(
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

    override fun onBindViewHolder(holder: ShopItemViewHolder.ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
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

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).enabled) {
            true -> ACTIVE
            false -> NOT_ACTIVE
        }
    }

    companion object {
        const val ACTIVE = 101
        const val NOT_ACTIVE = 100
        const val MAX_POOL_SIZE = 15
    }
}
