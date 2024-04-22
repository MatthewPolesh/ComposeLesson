package com.example.composelesson.AccountScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.composelesson.MenuScreen.Logo

@Composable
fun ShoppingHeader(font_caveat: FontFamily) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp, 15.dp, 10.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Logo(font = font_caveat)
    }
}