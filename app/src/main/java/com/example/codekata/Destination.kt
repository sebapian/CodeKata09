package com.example.codekata

sealed class Destination(val route: String) {
    object Shop: Destination("Shop")
    object Cart: Destination("Cart")

}
