package com.example.composelesson.MenuScreen

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import com.example.composelesson.MainViewModel
import com.example.composelesson.R

@Composable
fun MenuHeader(font_caveat: FontFamily, font_montserrat: FontFamily, viewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding( top= 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Logo(font = font_caveat)

        Spacer(modifier = Modifier.weight(1F))

        Location_Adress(font = font_montserrat)

    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Location_Adress(font: FontFamily) {


    val list = listOf("Москва", "Санкт-Петербург", "Новосибирск")
    val expanded = remember { mutableStateOf(false) }
    val delayFinished = remember { mutableStateOf(false) }
    val current_value = remember { mutableStateOf(list[0]) }
    val widthModifier =
        if (expanded.value)
            Modifier
                .width(167.5.dp)
                .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        else Modifier
            .wrapContentWidth()
            .clip(shape = RoundedCornerShape(20.dp))


    Box(
        modifier = widthModifier
            .height(30.dp)
            .background(color = colorResource(id = R.color.element_background))
            .animateContentSize()
            .padding()
            .clickable {
                expanded.value = !expanded.value
            },
        contentAlignment = Alignment.Center

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = null,
                tint = colorResource(id = R.color.white),
                modifier = Modifier
                    .size(25.dp)
                    .padding(start = 8.dp, end = 5.dp),
            )
            Text(
                text = current_value.value,
                fontFamily = font,
                color = colorResource(id = R.color.white),
                fontSize = 13.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        if (expanded.value) {
            Popup(
                onDismissRequest = { },
                popupPositionProvider = PopupPositionProvider,
            ) {
                Surface(
                    shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                    color = colorResource(id = R.color.element_background),
                    modifier = Modifier
                        .width(167.5.dp)
                        .animateContentSize()
                ) {
                    Column {
                        list.forEach { item ->
                            DropdownMenuItem(onClick = {
                                expanded.value = false
                                delayFinished.value = false
                                current_value.value = item
                            },
                                text = {
                                    Text(
                                        text = item,
                                        fontSize = 12.sp,
                                        fontFamily = font,
                                        color = colorResource(id = R.color.white)
                                    )
                                })
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Logo(font: FontFamily) {
    val image_modifier = Modifier
        .size(40.dp, 40.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .background(color = colorResource(id = R.color.yellow))

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "",
        modifier = image_modifier,
        contentScale = ContentScale.Fit
    )
    Text(
        modifier = Modifier.padding(start = 10.dp),
        text = "Fast & Delicious",
        color = colorResource(id = R.color.white),
        fontSize = 20.sp,
        fontFamily = font
    )
}

object PopupPositionProvider : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val x = anchorBounds.left
        val y = anchorBounds.top + anchorBounds.height
        return IntOffset(x, y)
    }
}
