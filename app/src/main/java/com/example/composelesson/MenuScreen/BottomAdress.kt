package com.example.composelesson.AccountScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.substring
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.MenuScreen.Adress
import com.example.composelesson.R
import my.app.android.Mask

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAdressAlert(
    font_m_regular: FontFamily,
    font_m_semibold: FontFamily,
    font_m_light: FontFamily,
    adress: MutableState<Adress>,
    showDialog: MutableState<Boolean>,
) {

    var newAdress by remember { mutableStateOf(adress.value.adress) }
    var newHouse by remember { mutableStateOf(adress.value.house.substring(3)) }
    var newFlat by remember { mutableStateOf(adress.value.flat.substring(3)) }

    ModalBottomSheet(
        containerColor = colorResource(id = R.color.element_background),
        onDismissRequest = { showDialog.value = !showDialog.value },
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = "Адресс доставки",
                fontFamily = font_m_semibold,
                fontSize = 20.sp,
                color = colorResource(id = R.color.white),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            OutlinedTextField(
                value = newAdress,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = colorResource(id = R.color.white),
                    unfocusedIndicatorColor = colorResource(id = R.color.white),
                    containerColor = colorResource(id = R.color.element_background),
                    cursorColor = colorResource(id = R.color.yellow)
                ),
                shape = RoundedCornerShape(15.dp),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.white),
                    fontFamily = font_m_regular,
                    fontSize = 15.sp

                ),
                onValueChange = { if (it.length <= 30) newAdress = it },
                label = {
                    Text(
                        text = "Адресс",
                        fontFamily = font_m_light,
                        fontSize = 10.sp,
                        color = colorResource(id = R.color.white)
                    )
                },
            )
            Row {
                OutlinedTextField(
                    value = newHouse,
                    modifier = Modifier.width(150.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = colorResource(id = R.color.white),
                        unfocusedIndicatorColor = colorResource(id = R.color.white),
                        containerColor = colorResource(id = R.color.element_background),
                        cursorColor = colorResource(id = R.color.yellow)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.white),
                        fontFamily = font_m_regular,
                        fontSize = 15.sp
                    ),
                    onValueChange = { if (it.length <= 5) newHouse = it },
                    label = {
                        Text(
                            text = "Дом",
                            fontFamily = font_m_light,
                            fontSize = 10.sp,
                            color = colorResource(id = R.color.white)
                        )
                    },
                )
                Spacer(modifier = Modifier.weight(1f))
                OutlinedTextField(
                    value = newFlat,
                    modifier = Modifier.width(150.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = colorResource(id = R.color.white),
                        unfocusedIndicatorColor = colorResource(id = R.color.white),
                        containerColor = colorResource(id = R.color.element_background),
                        cursorColor = colorResource(id = R.color.yellow)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.white),
                        fontFamily = font_m_regular,
                        fontSize = 15.sp

                    ),
                    onValueChange = { if (it.length <= 5) newFlat = it },
                    label = {
                        Text(
                            text = "Квартира",
                            fontFamily = font_m_light,
                            fontSize = 10.sp,
                            color = colorResource(id = R.color.white)
                        )
                    },
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    showDialog.value = !showDialog.value
                    adress.value.adress = newAdress
                    adress.value.house = " , $newHouse"
                    adress.value.flat = " , $newFlat"
                },
                content = {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material.Text(
                            text = "Сохранить",
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
}