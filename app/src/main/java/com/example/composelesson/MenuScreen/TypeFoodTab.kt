package com.example.composelesson.MenuScreen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.composelesson.R

@Composable
fun TypeFoodTab(
    font_m_light: FontFamily,
    listState: LazyListState,
    selectedTabIndex: MutableState<Int>,
    coroutineScope: CoroutineScope,
    selectedTabColor: Color,
    unselectedTabColor: Color,
    tabs: List<String>,
    TabColor: MutableList<Color>
) {

    ScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = selectedTabIndex.value,
        modifier = Modifier
            .wrapContentHeight()
            .padding(bottom = 10.dp)
            .clip(shape = RoundedCornerShape(20.dp)),
        divider = {},
        backgroundColor = colorResource(id = R.color.element_background),
        indicator = {},

    ) {
        tabs.forEachIndexed { index, title ->

            Tab(
                selected = selectedTabIndex.value == index,
                modifier = Modifier.height(30.dp),
                onClick = {
                    selectedTabIndex.value = index
                    TabColor[index] = selectedTabColor
                    coroutineScope.launch {
                        listState.animateScrollToItem(index * 5,
                          )
                    }
                },
                text = {Text(
                    text = title,
                    color = if (selectedTabIndex.value == index) selectedTabColor else unselectedTabColor,
                    modifier = Modifier
                        .padding(vertical = 0.dp),
                    textAlign = TextAlign.Center,
                    fontFamily = font_m_light,
                    fontSize = 11.sp
                )}
            )

        }
    }
}



