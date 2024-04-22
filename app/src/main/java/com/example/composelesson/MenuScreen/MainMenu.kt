package com.example.composelesson.MenuScreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import com.example.composelesson.R
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map


@Composable
fun MainMenu(
    font_m_regular:FontFamily,
    font_m_light:FontFamily,
    font_m_semibold:FontFamily,
) {
    val Meel1 = Meel ("Пицца1", "Описание",999, FoodType.PIZZA)
    val Meel2 = Meel ("Пицца2", "Описание",999, FoodType.PIZZA)
    val Meel3 = Meel ("Пицца3", "Описание",999, FoodType.PIZZA)
    val Meel4 = Meel ("Пицца4", "Описание",999, FoodType.PIZZA)
    val Meel5 = Meel ("Пицца5", "Описание",999, FoodType.PIZZA)
    val Meel6 = Meel ("Бургер1", "Описание",999, FoodType.BURGERS)
    val Meel7 = Meel ("Бургер2", "Описание",999, FoodType.BURGERS)
    val Meel8 = Meel ("Бургер3", "Описание",999, FoodType.BURGERS)
    val Meel9 = Meel ("Бургер4", "Описание",999, FoodType.BURGERS)
    val Meel10 = Meel ("Бургер5", "Описание",999, FoodType.BURGERS)
    val Meel11 = Meel ("Паста1", "Описание",999, FoodType.PASTA)
    val Meel12 = Meel ("Паста2", "Описание",999, FoodType.PASTA)
    val Meel13= Meel ("Паста3", "Описание",999, FoodType.PASTA)
    val Meel14 = Meel ("Паста4", "Описание",999, FoodType.PASTA)
    val Meel15 = Meel ("Паста5", "Описание",999, FoodType.PASTA)
    val Meel16 = Meel ("Вок1", "Описание",999, FoodType.WOK)
    val Meel17 = Meel ("Вок2", "Описание",999, FoodType.WOK)
    val Meel18 = Meel ("Вок3", "Описание",999, FoodType.WOK)
    val Meel19 = Meel ("Вок4", "Описание",999, FoodType.WOK)
    val Meel20 = Meel ("Вок5", "Описание",999, FoodType.WOK)
    val Meel21 = Meel ("Салат1", "Описание",999, FoodType.SALADS)
    val Meel22 = Meel ("Салат2", "Описание",999, FoodType.SALADS)
    val Meel23 = Meel ("Салат3", "Описание",999, FoodType.SALADS)
    val Meel24 = Meel ("Салат4", "Описание",999, FoodType.SALADS)
    val Meel25 = Meel ("Салат5", "Описание",999, FoodType.SALADS)
    val Meel26 = Meel ("Напиток1", "Описание",999, FoodType.DRINKS)
    val Meel27 = Meel ("Напиток2", "Описание",999, FoodType.DRINKS)
    val Meel28= Meel ("Напиток3", "Описание",999, FoodType.DRINKS)
    val Meel29 = Meel ("Напиток4", "Описание",999, FoodType.DRINKS)
    val Meel30 = Meel ("Напиток5", "Описание",999, FoodType.DRINKS)



    val mealMenu = remember {
        listOf(
            Meel1, Meel2, Meel3, Meel4,
            Meel5, Meel6, Meel7, Meel8,
            Meel9, Meel10, Meel11, Meel12,
            Meel13, Meel14, Meel15, Meel16,
            Meel17, Meel18, Meel19, Meel20,
            Meel21, Meel22, Meel23, Meel24,
            Meel25, Meel26, Meel27, Meel28,
            Meel29, Meel30
        )
    }


    val selectedTabIndex = remember { mutableStateOf(0) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val selectedTabColor = colorResource(id = R.color.yellow)
    val unselectedTabColor = colorResource(id = R.color.white)
    val TabColor = remember{ mutableStateListOf<Color>() }
    val tabs = listOf("Пицца", "Бургеры", "Пасты", "Воки", "Салаты", "Напитки")
    for (i in tabs.indices)
    {
        TabColor.add(if (i == 0) selectedTabColor else  unselectedTabColor)
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            MenuList(
                font_m_light = font_m_light,
                font_m_regular = font_m_regular,
                font_m_semibold = font_m_semibold,
                listState = listState,
                meelMenu = mealMenu
            )


            TypeFoodTab(font_m_light = font_m_light,
                listState = listState,
                selectedTabIndex = selectedTabIndex,
                coroutineScope = coroutineScope,
                selectedTabColor= selectedTabColor,
                unselectedTabColor= unselectedTabColor,
                TabColor = TabColor,
                tabs = tabs)

        }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index ->
                when (index) {
                    in 0..4 -> 0
                    in 5..9 -> 1
                    in 10..14 -> 2
                    in 15..19 -> 3
                    in 20..24 -> 4
                    in 25..29 -> 5
                    else -> 0
                }
            }
            .distinctUntilChanged()
            .collect { index ->
                selectedTabIndex.value = index


            }
    }
}




