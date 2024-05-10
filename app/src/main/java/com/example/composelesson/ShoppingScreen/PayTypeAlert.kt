package com.example.composelesson.ShoppingScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.MainViewModel
import com.example.composelesson.R


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PayTypeAlert(
    font_m_semibold: FontFamily,
    font_m_regular: FontFamily,
    showAlert: MutableState<Boolean>,
    viewModel: MainViewModel,

    ) {
    AlertDialog(
        containerColor = colorResource(id = R.color.element_background),
        title = {
            Text(
                text = "Cпособ оплаты:",
                fontFamily = font_m_semibold,
                color = colorResource(id = R.color.white)
            )
        },
        text = {
            Box {
                Column(
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                            .clickable {
                                showAlert.value = !showAlert.value
                                viewModel.changePayingType("Картой")
                                       },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.card),
                            contentDescription = "",
                            tint = colorResource(id = R.color.white),
                            modifier = Modifier
                                .size(28.dp)
                                .padding(end = 5.dp)
                        )
                        Text(
                            text = "Картой",
                            fontFamily = font_m_regular,
                            color = colorResource(id = R.color.white),
                            fontSize = 18.sp,
                            )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showAlert.value = !showAlert.value
                                viewModel.changePayingType("Наличными")
                                       },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.money),
                            contentDescription = "",
                            tint = colorResource(id = R.color.white),
                            modifier = Modifier
                                .size(28.dp)
                                .padding(end = 5.dp)
                        )
                        Text(
                            text = "Наличными",
                            fontFamily = font_m_regular,
                            color = colorResource(id = R.color.white),
                            fontSize = 18.sp,
                            )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {},
        onDismissRequest = { showAlert.value = !showAlert.value },
    )
}