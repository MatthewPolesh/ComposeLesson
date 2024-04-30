package com.example.composelesson.MenuScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composelesson.MainViewModel
import com.example.composelesson.R

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun MenuScreen(viewModel: MainViewModel) {
    val caveat = FontFamily(Font(R.font.caveat, FontWeight.Normal))
    val montserrat_regular = FontFamily(Font(R.font.montserratalternatesregular, FontWeight.Normal))
    val montserrat_light = FontFamily(Font(R.font.montserratalternateslight, FontWeight.Normal))
    val montserrat_semibold = FontFamily(Font(R.font.montserratalternatessemibold, FontWeight.Normal))


            Column(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.background))
                    .padding(horizontal = 10.dp)
                    .fillMaxSize(1F)
            )

            {
                MenuHeader(
                    font_caveat = caveat,
                    font_montserrat = montserrat_regular,
                    viewModel = viewModel)

                AdressBox(
                    font_m_regular = montserrat_regular,
                    font_m_semibold = montserrat_semibold,
                    font_m_light = montserrat_light,
                    viewModel = viewModel
                )

                MainMenu(
                    font_m_regular = montserrat_regular,
                    font_m_light = montserrat_light,
                    font_m_semibold = montserrat_semibold,
                    viewModel = viewModel)
            }

}