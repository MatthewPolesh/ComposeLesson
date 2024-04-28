package com.example.composelesson.MenuScreen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.composelesson.MainViewModel
import com.example.composelesson.R
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainMenu(
    font_m_regular: FontFamily,
    font_m_light: FontFamily,
    font_m_semibold: FontFamily,
    viewModel: MainViewModel,
) {

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
                viewModel
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




