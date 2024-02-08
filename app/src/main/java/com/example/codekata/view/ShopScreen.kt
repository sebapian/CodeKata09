package com.example.codekata.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.codekata.R
import com.example.codekata.util.ReadPriceRuleCsv.readCsv
import com.example.codekata.viewmodel.CartViewModel

@Composable
fun ShopScreen(paddingValues: PaddingValues, cartViewModel: CartViewModel) {

    val context = LocalContext.current
    val shopItems = readCsv(context.resources.openRawResource(R.raw.price_rules))

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        items(shopItems) { shopItem ->
            ShopItem(shopItem = shopItem, cartViewModel = cartViewModel, context = context)
        }
    }
}