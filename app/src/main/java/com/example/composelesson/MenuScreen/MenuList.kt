package com.example.composelesson.MenuScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.composelesson.R

@Composable
fun ColumnScope.MenuList(
    font_m_light: FontFamily,
    font_m_regular: FontFamily,
    font_m_semibold: FontFamily,
    listState: LazyListState,
    meelMenu: List<Meel>


) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(bottom = 10.dp)
            .border(
                width = 0.5.dp,
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(20.dp)
            )

    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = colorResource(id = R.color.background))
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)
        ) {
            itemsIndexed(meelMenu) { index, item ->
                Item(
                    font_m_semibold = font_m_semibold,
                    font_m_regular = font_m_regular,
                    font_m_light = font_m_light,
                    meel = item
                )
            }
        }
    }
}