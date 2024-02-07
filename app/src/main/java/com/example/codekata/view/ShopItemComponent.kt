package com.example.codekata.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codekata.R
import com.example.codekata.model.Rule
import com.example.codekata.viewmodel.CartViewModel

@Composable
fun ShopItem(shopItem: Rule, cartViewModel: CartViewModel, context: Context) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = shopItem.sku,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = "Price: ${shopItem.price}",
                    fontSize = 16.sp
                )
                if (shopItem.batchSize != null) Text(
                    text = "Deal: ${shopItem.batchSize} for ${shopItem.batchPrice}",
                    fontSize = 16.sp,
                    color = Color.Red
                )

            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val itemToAdd = rememberSaveable { mutableStateOf(linkedMapOf<String, Int>()) }
                val counter = rememberSaveable { mutableStateOf(0) }
                Row(
                    modifier = Modifier.weight(1f, true),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    IconButton(onClick = {
                        if (counter.value > 0) {
                            counter.value -= 1
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_remove),
                            contentDescription = null
                        )
                    }
                    Text(text = "${counter.value}", fontSize = 22.sp)
                    IconButton(onClick = {
                        counter.value += 1
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null
                        )
                    }
                }

                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Added ${counter.value} of ${shopItem.sku} into cart.",
                            Toast.LENGTH_SHORT
                        ).show()
                        itemToAdd.value[shopItem.sku] = counter.value
                        cartViewModel.addToCart(itemToAdd.value)
                        counter.value = 0
                    },
                    modifier = Modifier.size(40.dp, 40.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_to_cart),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
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