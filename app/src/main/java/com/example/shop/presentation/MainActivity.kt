package com.example.shop.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.domain.ShopItem
import com.example.shop.domain.ShopListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycler()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        viewModel.shopList.observe(this) {
//            Log.d("Main", it.toString())
//        }
//        viewModel.deleteShopList(ShopItem("Name 0", 0, true, 0))
//        viewModel.editShopList(ShopItem("Name 1", 1, true, 1))
        // val list = listOf<ShopItem>(ShopItem("Name 1", 1, true, 1),ShopItem("Name 2", 2, true, 2))

        viewModel.shopList.observe(this) {
            adapter.shoplist = it
        }
    }

    private fun setupRecycler() {
        val rvShopList = findViewById<RecyclerView>(R.id.shop_list)
        adapter = ShopListAdapter()
        rvShopList.adapter = adapter
    }

}