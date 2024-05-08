package com.example.composelesson.AccountScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.MainViewModel
import com.example.composelesson.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardsColumn(
    font_m_regular: FontFamily,
    showDialog: MutableState<Boolean>,
    viewModel: MainViewModel
) {
    val cards = viewModel.cards.collectAsState()
    Column {
        Text(
            text = "Мои карты",
            fontSize = 20.sp,
            fontFamily = font_m_regular,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(bottom = 10.dp)
        )
        LazyColumn {
            itemsIndexed(cards.value) { index, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.card),
                        contentDescription = "card",
                        tint = colorResource(id = R.color.white),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(30.dp)
                    )
                    Text(
                        text = "**** " + item.number.takeLast(4),
                        fontSize = 20.sp,
                        fontFamily = font_m_regular,
                        color = colorResource(id = R.color.white)
                    )
                    Spacer(modifier = Modifier.weight(1F))

                    val interactionSource = remember { MutableInteractionSource() }
                    val indication = rememberRipple(bounded = true)
                    Icon(
                        painter = painterResource(id = R.drawable.cross),
                        contentDescription = "cross",
                        tint = colorResource(id = R.color.white),
                        modifier = Modifier
                            .size(20.dp)
                            .padding(0.dp)
                            .clip(shape = CircleShape)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = indication,
                                onClick = { viewModel.deleteCard(item) })
                    )

                }
            }
        }
        Spacer(modifier = Modifier.weight(1F))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = { showDialog.value =! showDialog.value },
            content = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Добавить карту",
                        fontFamily = font_m_regular,
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.background)

                    )
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.yellow)
            )
        )
    }
}