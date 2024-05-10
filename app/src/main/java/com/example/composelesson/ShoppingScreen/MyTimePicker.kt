package com.example.composelesson.ShoppingScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.R


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MyTimePicker(
    font_m_regular: FontFamily,
    SelectedHourIndex: MutableState<Int>,
    SelectedMinuteIndex: MutableState<Int>,
    Hours: List<String>,
    Minutes: List<String>
) {

    val listHourState = rememberLazyListState(40)
    val listMinuteState = rememberLazyListState(40)

    Box(
        modifier = Modifier
            .wrapContentWidth()
            .height(60.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.element_background))

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(60.dp)
                    .padding(horizontal = 6.dp)
            ) {
                LazyColumn(
                    state = listHourState,
                    modifier = Modifier.padding(vertical = 5.dp)
                ) {
                    itemsIndexed(Hours) {
                        index, number ->
                        SelectedHourIndex.value = listHourState.firstVisibleItemIndex
                        Text(
                            text = number,
                            modifier = Modifier
                                .width(60.dp),
                            textAlign = TextAlign.Center,
                            fontFamily = font_m_regular,
                            fontSize = 35.sp,
                            color = colorResource(id = R.color.white),
                        )
                    }
                }
                LaunchedEffect(SelectedHourIndex.value) {
                    listHourState.animateScrollToItem(SelectedHourIndex.value)
                    SelectedHourIndex.value = listHourState.firstVisibleItemIndex
                }

            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(shape = CircleShape)
                        .background(color = colorResource(id = R.color.yellow))
                )

                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(shape = CircleShape)
                        .background(color = colorResource(id = R.color.yellow))
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(60.dp)
                    .padding(horizontal = 5.dp)
            ) {
                LazyColumn(
                    state = listMinuteState,
                    modifier = Modifier.padding(vertical = 5.dp)
                ) {
                    itemsIndexed(Minutes) {
                            index, number ->
                        SelectedMinuteIndex.value = listMinuteState.firstVisibleItemIndex
                        Text(
                            text = number,
                            modifier = Modifier
                                .width(60.dp),
                            textAlign = TextAlign.Center,
                            fontFamily = font_m_regular,
                            fontSize = 35.sp,
                            color = colorResource(id = R.color.white),
                        )
                    }
                }
                LaunchedEffect(SelectedMinuteIndex.value) {
                    listMinuteState.animateScrollToItem(SelectedMinuteIndex.value)
                    SelectedMinuteIndex.value = listMinuteState.firstVisibleItemIndex
                }
            }
        }
    }
}

