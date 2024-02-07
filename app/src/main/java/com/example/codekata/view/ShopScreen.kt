package com.example.codekata.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.codekata.R
import com.example.codekata.ReadPriceRuleCsv.readCsv
import com.example.codekata.viewmodel.CartViewModel

@Composable
fun ShopScreen(paddingValues: PaddingValues, context: Context, cartViewModel: CartViewModel) {

    val shopItems = readCsv(context.resources.openRawResource(R.raw.price_rules))

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = paddingValues.calculateBottomPadding())) {
        items(shopItems) { shopItem ->
            ShopItem(shopItem = shopItem, cartViewModel = cartViewModel, context = context)
        }
    }
}