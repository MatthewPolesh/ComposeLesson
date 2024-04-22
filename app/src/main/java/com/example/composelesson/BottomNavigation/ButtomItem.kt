package com.example.composelesson.BottomNavigation

import com.example.composelesson.R

enum class Screens(val value: String){
    SCREEN1("account"),
    SCREEN2("menu"),
    SCREEN3("shopcart"),
}

sealed class BottomItem(val title: String, var iconId: Int, val route:String) {
    object Screen1: BottomItem("Аккаунт", iconId = R.drawable.account, Screens.SCREEN1.value)
    object Screen2: BottomItem("Меню", iconId = R.drawable.menu, Screens.SCREEN2.value)
    object Screen3: BottomItem("Корзина", iconId = R.drawable.shopcart, Screens.SCREEN3.value)

}