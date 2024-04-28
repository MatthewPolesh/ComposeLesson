package com.example.composelesson.MenuScreen

data class Meel(val name: String,
                val description: String,
                val price: Int,
                val type: FoodType,
                var counter: Int = 0,
                var showPrice: Boolean = false)

