package com.example.codekata.view

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.codekata.ReadPriceRuleCsv
import com.example.codekata.viewmodel.CartViewModel

@Composable
fun CartScreen(navController: NavHostController, cartViewModel: CartViewModel, paddingValues: PaddingValues, context: Context) {

    val cartItems = remember { cartViewModel.cart }
    val totalPrice = remember { cartViewModel.totalPrice }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .weight(7f)) {
            Log.d("SKU", cartItems.toString())
            cartItems.forEach { cartItem ->
                item {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = cartItem.key,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 40.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Row(
                                    modifier = Modifier.weight(1f, true),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {

                                    IconButton(onClick = {
                                        cartViewModel.removeFromCart(cartItem.key)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_remove),
                                            contentDescription = null
                                        )
                                    }
                                    Text(text = "${cartItem.value}", fontSize = 22.sp)

                                }
                            }

                        }
                        Divider(
                            color = Color.LightGray,
                            modifier = Modifier.padding(
                                top = 4.dp,
                                bottom = 4.dp,
                                start = 20.dp,
                                end = 20.dp
                            )
                        )

                    }
                }
            }
        }

        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Total:", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(text = "${totalPrice.value}", modifier= Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Right)
        }
    }
}