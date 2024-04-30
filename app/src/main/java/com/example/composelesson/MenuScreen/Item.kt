package com.example.composelesson.MenuScreen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.MainViewModel
import com.example.composelesson.R

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Item(
    font_m_semibold: FontFamily,
    font_m_regular: FontFamily,
    font_m_light: FontFamily,
    meal: Meel,
    viewModel: MainViewModel,
) {

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .background(color = colorResource(id = R.color.background))
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .border(
                        width = 0.5.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentScale = ContentScale.Fit,
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .height(90.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = meal.name,
                    fontFamily = font_m_semibold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white)
                )
                Text(
                    text = meal.description,
                    fontFamily = font_m_light,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.white)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = colorResource(id = R.color.element_background))
                        .clickable {
                            viewModel.increaseCounter(meal.name)
                        })
                {
                    if (!meal.showPrice) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = meal.price.toString() + "₽",
                            fontSize = 14.sp,
                            fontFamily = font_m_regular,
                            color = colorResource(id = R.color.white)
                        )
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cross),
                                contentDescription = "",
                                tint = colorResource(id = R.color.white),
                                modifier = Modifier
                                    .padding(start = 8.dp, end = 5.dp, top = 8.dp, bottom = 8.dp)
                                    .size(18.dp)
                                    .clickable {
                                        viewModel.decreaseCounter(meal.name)
                                    })
                            Text(
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 8.dp),
                                text = meal.counter.toString(),
                                fontSize = 14.sp,
                                fontFamily = font_m_regular,
                                color = colorResource(id = R.color.white)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.plus),
                                contentDescription = "",
                                tint = colorResource(id = R.color.white),
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                                    .size(18.dp)
                                    .clickable {
                                        viewModel.increaseCounter(meal.name)
                                    })
                        }

                    }

                }
            }
        }
    }
}
