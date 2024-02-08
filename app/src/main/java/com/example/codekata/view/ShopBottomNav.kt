package com.example.codekata.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.codekata.destination.Destination
import com.example.codekata.R

@Composable
fun ShopBottomNav(navController: NavHostController) {
    BottomNavigation() {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Shop.route,
            onClick = { navController.navigate(Destination.Shop.route) {
                popUpTo(Destination.Shop.route)
                launchSingleTop = true
            } },
            icon = { Icon(painter = painterResource(id = R.drawable.ic_shop), contentDescription = null) },
            label = { Text(text = Destination.Shop.route) }
        )
        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Cart.route,
            onClick = { navController.navigate(Destination.Cart.route) {
                popUpTo(Destination.Cart.route)
                launchSingleTop = true
            } },
            icon = { Icon(painter = painterResource(id = R.drawable.ic_cart), contentDescription = null) },
            label = { Text(text = Destination.Cart.route) }
        )
    }
}