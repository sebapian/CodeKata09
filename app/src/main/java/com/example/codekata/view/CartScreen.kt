package com.example.codekata.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codekata.viewmodel.CartViewModel

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    paddingValues: PaddingValues,
) {

    val cartItems = remember { cartViewModel.cart }
    val totalPrice = remember { cartViewModel.totalPrice }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(7f)
        ) {
            cartItems.forEach { cartItem ->
                item {
                    CartItem(cartItem = cartItem, cartViewModel = cartViewModel)
                }
            }
        }

        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Total:",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = "$${totalPrice.value}",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Right
            )
        }
    }
}