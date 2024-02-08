package com.example.codekata.destination

sealed class Destination(val route: String) {
    object Shop: Destination("Shop")
    object Cart: Destination("Cart")

}
