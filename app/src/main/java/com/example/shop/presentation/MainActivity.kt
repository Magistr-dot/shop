package com.example.shop.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycler()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
        val buttonAdd = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAdd.setOnClickListener {
            val intent = ShopItemActivity.newIntentAdd(this)
            startActivity(intent)
        }
    }

    private fun setupRecycler() {

        val rvShopList = findViewById<RecyclerView>(R.id.shop_list)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter

            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.ACTIVE,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.NOT_ACTIVE,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        longClick()
        shortClick()
        swipe(rvShopList)
    }

    private fun swipe(rvShopList: RecyclerView) {
        val mIth = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
                ): Boolean {
                    return true // true if moved, false otherwise
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteShopList(item)
                }
            })
        mIth.attachToRecyclerView(rvShopList)
    }

    private fun shortClick() {
        shopListAdapter.onShopItemShortClickListener = {
            Log.d("Test", it.id.toString())
            val intent = ShopItemActivity.newIntentEdit(this, it.id)
            startActivity(intent)
        }
    }


    private fun longClick() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.editShopList(it)
        }
    }
}

