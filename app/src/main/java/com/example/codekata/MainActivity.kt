package com.example.codekata

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codekata.ui.theme.CodeKataTheme
import com.example.codekata.view.CartScreen
import com.example.codekata.view.ShopBottomNav
import com.example.codekata.view.ShopScreen
import com.example.codekata.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val cartViewModel by viewModels<CartViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeKataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val context = applicationContext
                    ShoppingScaffold(navController = navController, context = context, cartViewModel = cartViewModel)
                }
            }
        }
    }
}

@Composable
fun ShoppingScaffold(navController: NavHostController, context: Context, cartViewModel: CartViewModel) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { ShopBottomNav(navController = navController)}
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Destination.Shop.route) {
            composable(Destination.Shop.route) {
                ShopScreen(paddingValues = paddingValues, context = context, cartViewModel = cartViewModel)
            }
            composable(Destination.Cart.route) {
                CartScreen(navController = navController, cartViewModel = cartViewModel, paddingValues = paddingValues, context = context)
            }
        }
    }
}