package com.example.composelesson.ShoppingScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.AccountScreen.BottomAdressAlert
import com.example.composelesson.MainViewModel
import com.example.composelesson.MenuScreen.Adress
import com.example.composelesson.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DeliveryBox(
    font_m_light: FontFamily,
    font_m_regular: FontFamily,
    font_m_semibold: FontFamily,
    viewModel: MainViewModel
) {
    val adress = viewModel.selectedAdress.collectAsState()
    val showAdressDialog = remember { mutableStateOf(false) }


    if (showAdressDialog.value)
        BottomAdressAlert(
            font_m_regular = font_m_regular,
            font_m_semibold = font_m_semibold,
            font_m_light = font_m_light,
            adress = adress,
            showDialog = showAdressDialog,
            viewModel = viewModel,
        )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable { showAdressDialog.value = !showAdressDialog.value }
            .background(
                color = colorResource(id = R.color.element_background)
            )

    ) {
        Text(
            text = if (adress.value.adress == "") "Выберите адрес доставки" else "${adress.value.adress}, д. ${adress.value.house}, кв. ${adress.value.flat}",
            fontFamily = font_m_light,
            maxLines = 1,
            fontSize = 14.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(5.dp)
        )

    }
}