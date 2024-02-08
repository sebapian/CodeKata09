package com.example.codekata.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codekata.R
import com.example.codekata.viewmodel.CartViewModel

@Composable
fun CartItem(cartItem: Map.Entry<String, Int>, cartViewModel: CartViewModel) {
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
                        cartViewModel.calculateCartTotal()
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
