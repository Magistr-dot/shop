package com.example.shop.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shop.R
import com.example.shop.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {

    private lateinit var viewModel: ShopItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var sButton: Button

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews()
        addTextChangeListener()
        launchMode()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.errorCount.observe(this) {
            val mes = if (it) {
                getString(R.string.error_count)
            } else {
                null
            }
            tilCount.error = mes
        }
        viewModel.error.observe(this) {
            val mes = if (it) {
                getString(R.string.error_count)
            } else {
                null
            }
            tilName.error = mes
        }
        viewModel.shouldCloseScreen.observe(this) {
            finish()
        }
    }

    private fun launchMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListener() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetError()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorCount()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun launchAddMode() {
        sButton.setOnClickListener {
            viewModel.addShopItem(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(this) {
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        sButton.setOnClickListener {
            viewModel.editShopList(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown mode$mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(SHOP_ID)) {
                throw RuntimeException("Param shop is absent")
            }
            shopItemId = intent.getIntExtra(SHOP_ID, ShopItem.UNDEFINED_ID)
        }
    }

    private fun initViews() {
        tilName = findViewById(R.id.til_name)
        tilCount = findViewById(R.id.til_count)
        etName = findViewById(R.id.et_name)
        etCount = findViewById(R.id.et_count)
        sButton = findViewById(R.id.save_button)
    }

    companion object {
        private const val EXTRA_MODE = "extra_mode"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val SHOP_ID = "shop_id"
        private const val MODE_UNKNOWN = ""


        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEdit(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_EDIT)
            intent.putExtra(SHOP_ID, shopItemId)
            return intent
        }
    }
}

