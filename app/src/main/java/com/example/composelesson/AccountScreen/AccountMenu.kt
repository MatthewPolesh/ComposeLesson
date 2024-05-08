package com.example.composelesson.AccountScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.MainViewModel
import com.example.composelesson.R
import my.app.android.Mask


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AccountMenu(
    font_m_semibold: FontFamily,
    font_m_regular: FontFamily,
    font_m_light: FontFamily,
    viewModel: MainViewModel
) {
    val registrationFlag = viewModel.registrationFlag.collectAsState()
    val entranceFlag = viewModel.entranceFlag.collectAsState()

    val showAlertAccount = remember { mutableStateOf(false)}
    val showAlertEntrance = remember { mutableStateOf(false)}
    val showAlertRegistration = remember { mutableStateOf(false) }
    val showDialogCard = remember { mutableStateOf(false) }

    val card = remember { mutableStateOf(Card("","","")) }
    val cardsList = remember { mutableStateOf<List<Card>>(emptyList()) }
    val cardMask = Mask("####-####-####-####")
    val dateMask = Mask("##/##")
    val phoneMask = Mask( "+7 (###) ### ##-##")

    if (showAlertAccount.value){
        AccountAlert(
            font_m_semibold = font_m_semibold,
            font_m_regular = font_m_regular,
            font_m_light = font_m_light,
            showAlert = showAlertAccount,
            phoneMask = phoneMask,
            viewModel = viewModel
            )
    }
    if (showAlertRegistration.value)
        AccountRegistration(
            font_m_regular = font_m_regular,
            font_m_light = font_m_light,
            font_m_semibold = font_m_semibold,
            showAlert = showAlertRegistration,
            phoneMask = phoneMask,
            viewModel = viewModel
        )
    if (showAlertEntrance.value)
        AccountEntrance(
            font_m_regular = font_m_regular,
            font_m_light = font_m_light,
            font_m_semibold = font_m_semibold,
            showAlert = showAlertEntrance,
            phoneMask = phoneMask,
            viewModel = viewModel
        )

    if (showDialogCard.value)
        BottomCardAlert(
            font_m_regular = font_m_regular,
            font_m_semibold = font_m_semibold,
            font_m_light = font_m_light,
            showDialog = showDialogCard,
            cardMask = cardMask,
            dataMask= dateMask,
            viewModel = viewModel
        )
    if( registrationFlag.value or entranceFlag.value) {
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(color = colorResource(id = R.color.background))
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Профиль",
                        fontSize = 20.sp,
                        fontFamily = font_m_semibold,
                        color = colorResource(id = R.color.white)
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Button(
                        shape = RoundedCornerShape(20.dp),
                        onClick = { viewModel.Exit() },
                        content = {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Выйти",
                                    fontFamily = font_m_regular,
                                    color = colorResource(id = R.color.white)
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.element_background)
                        )
                    )
                }
                // номер и имя пользователя
                Column(
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.account),
                            contentDescription = "user",
                            modifier = Modifier.padding(end = 10.dp),
                            tint = colorResource(id = R.color.white)
                        )
                        Text(
                            text = viewModel.userName.value,
                            fontSize = 15.sp,
                            fontFamily = font_m_regular,
                            color = colorResource(id = R.color.white)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 20.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.phone),
                            contentDescription = "number",
                            modifier = Modifier.padding(end = 10.dp),
                            tint = colorResource(id = R.color.white)
                        )
                        BasicTextField(
                            onValueChange = {},
                            value = viewModel.userPhone.value,
                            readOnly = true,
                            textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = font_m_regular,
                            color = colorResource(id = R.color.white)),
                            visualTransformation = phoneMask
                        )

                    }
                }
                //Кнопка редактировать
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { showAlertAccount.value = !showAlertAccount.value },
                    content = {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Редактировать",
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
                CardsColumn(font_m_regular = font_m_regular, showDialog = showDialogCard, viewModel = viewModel)
            }
        }
    }
    //Без входа
    else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background)),
            contentAlignment = Alignment.Center
        ){
            Column {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { showAlertRegistration.value = !showAlertRegistration.value },
                    content = {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Зарегистрироваться",
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
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { showAlertEntrance.value = !showAlertEntrance.value},
                    content = {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Войти",
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
}