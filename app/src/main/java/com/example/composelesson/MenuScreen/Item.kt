package com.example.composelesson.MenuScreen

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.R

@Composable
fun Item(
    font_m_semibold: FontFamily,
    font_m_regular: FontFamily,
    font_m_light: FontFamily,
    meel: Meel){
    var clickFlag = remember { mutableStateOf(false) }
    var item = remember { mutableStateOf(meel) }
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 20.dp)
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
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = item.value.name,
                    fontFamily = font_m_semibold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white)
                )
                Text(
                    modifier = Modifier.padding(bottom = 25.dp),
                    text = item.value.description,
                    fontFamily = font_m_light,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.white)
                )
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = colorResource(id = R.color.element_background))
                        .clickable {
                            clickFlag.value = !clickFlag.value
                            item.value.counter++
                        }
                ){
                    if (!clickFlag.value) {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = item.value.price.toString() + "₽",
                            fontSize = 14.sp,
                            fontFamily = font_m_regular,
                            color = colorResource(id = R.color.white)
                        )
                    }
                    else
                    {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.cross),
                                contentDescription ="",
                                tint = colorResource(id = R.color.white),
                                modifier = Modifier.clickable { item.value.counter-- })
                            Text(
                                modifier = Modifier.padding(horizontal = 5.dp),
                                text = "${item.value.counter}",
                                fontSize = 14.sp,
                                fontFamily = font_m_regular,
                                color = colorResource(id = R.color.white)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.check),
                                contentDescription = "",
                                tint = colorResource(id = R.color.white),
                                modifier = Modifier.clickable { item.value.counter++ })
                        }

                    }

                }
            }
        }
    }
}
