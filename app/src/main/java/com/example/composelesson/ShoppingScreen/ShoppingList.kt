package com.example.composelesson.ShoppingScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.MainViewModel
import com.example.composelesson.MenuScreen.Item
import com.example.composelesson.R


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingList(
    font_m_semibold: FontFamily,
    font_m_regular: FontFamily,
    font_m_light: FontFamily,
    viewModel: MainViewModel
) {

    var newComment = viewModel.orderComment.collectAsState()
    val showPayingType = remember { mutableStateOf(false) }
    val showPicker = remember { mutableStateOf(false) }
    val state = rememberScrollState()
    val focusManager = LocalFocusManager.current

    val hourState = viewModel.selectedHour.collectAsState()
    val minuteState = viewModel.selectedMinute.collectAsState()
    val payingTypeState = viewModel.payingType.collectAsState()
    val shoppingList = viewModel.shoppingList.collectAsState()
    val payBottonFlag = viewModel.showButtonPayment.collectAsState()
    val orderStr = viewModel.orderStr.collectAsState()
    val timeFlag = viewModel.orderTimeFlag.collectAsState()

    val timeButtonMod1: Modifier
    val timeButtonMod2: Modifier
    val timeTextCol1: Color
    val timeTextCol2: Color

    if (showPayingType.value)
        PayTypeAlert(
            font_m_semibold = font_m_semibold,
            font_m_regular = font_m_regular,
            showAlert = showPayingType,
            viewModel = viewModel
        )

    if (showPicker.value)
        BottomTimePickerAlert(
            font_m_regular = font_m_regular,
            font_m_semibold = font_m_semibold,
            showPicker = showPicker,
            viewModel = viewModel
        )

    if (timeFlag.value) {
        timeButtonMod1 = Modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.yellow))
        timeButtonMod2 = Modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.element_background))
        timeTextCol1 = colorResource(id = R.color.element_background)
        timeTextCol2 = colorResource(id = R.color.white)
    } else {
        timeButtonMod1 = Modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.element_background))
        timeButtonMod2 = Modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.yellow))
        timeTextCol1 = colorResource(id = R.color.white)
        timeTextCol2 = colorResource(id = R.color.element_background)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .verticalScroll(state)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Заказ",
                fontFamily = font_m_semibold,
                fontSize = 20.sp,
                color = colorResource(id = R.color.white),
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { viewModel.clearList() },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "",
                        tint = colorResource(id = R.color.white),
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(15.dp)
                    )
                    Text(
                        text = "Очистить корзину",
                        fontFamily = font_m_light,
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.white),
                    )
                }

            }

        }
        Divider(modifier = Modifier.padding(vertical = 15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = RoundedCornerShape(20.dp))
                .border(
                    width = 0.5.dp,
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = colorResource(id = R.color.background))
                    .padding(horizontal = 10.dp)
            ) {

                for (item in shoppingList.value.asReversed()) {
                    Item(
                        font_m_semibold = font_m_semibold,
                        font_m_regular = font_m_regular,
                        font_m_light = font_m_light,
                        meal = item,
                        viewModel = viewModel,
                    )
                }
            }
        }

        Divider(modifier = Modifier.padding(top = 15.dp, bottom = 5.dp))


        OutlinedTextField(
            value = newComment.value,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = colorResource(id = R.color.white),
                unfocusedIndicatorColor = colorResource(id = R.color.white),
                containerColor = colorResource(id = R.color.element_background),
                cursorColor = colorResource(id = R.color.yellow),
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, capitalization = KeyboardCapitalization.Sentences),
            keyboardActions = KeyboardActions(onDone  = {focusManager.clearFocus()}, ),
            shape = RoundedCornerShape(15.dp),
            textStyle = TextStyle(
                color = colorResource(id = R.color.white),
                fontFamily = font_m_regular,
                fontSize = 15.sp

            ),
            onValueChange = { viewModel.changeOrderComment(it) },
            label = {
                Text(
                    text = "Комментарий к заказу",
                    fontFamily = font_m_light,
                    modifier = Modifier.padding(0.dp),
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.white)
                )
            },
        )

        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = "Доставка",
            fontFamily = font_m_semibold,
            fontSize = 20.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(bottom = 15.dp)
        )

        DeliveryBox(
            font_m_light = font_m_light,
            font_m_semibold = font_m_semibold,
            font_m_regular = font_m_regular,
            viewModel = viewModel
        )
        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Row(
            modifier = Modifier.padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Заказ приготовим",
                fontFamily = font_m_light,
                fontSize = 15.sp,
                color = colorResource(id = R.color.white)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Сегодня до ${hourState.value}:${minuteState.value}",
                fontFamily = font_m_light,
                fontSize = 15.sp,
                color = colorResource(id = R.color.white)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = timeButtonMod1
                    .clickable {
                        viewModel.changeOrderTimeFlag()
                        viewModel.changeOrderTime(
                            viewModel.orderHour.value,
                            viewModel.orderMinute.value
                        )
                    }
            ) {
                Text(
                    text = "Как можно скорее",
                    fontFamily = font_m_regular,
                    fontSize = 15.sp,
                    color = timeTextCol1,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = timeButtonMod2
                    .clip(shape = RoundedCornerShape(20.dp))
                    .clickable {
                        viewModel.changeOrderTimeFlag()
                        showPicker.value = !showPicker.value
                    }
            ) {
                Text(
                    text = "Ко времени",
                    fontFamily = font_m_regular,
                    fontSize = 15.sp,
                    color = timeTextCol2,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }

        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Row(
            modifier = Modifier.padding(bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Спопоб оплаты",
                fontFamily = font_m_regular,
                fontSize = 15.sp,
                color = colorResource(id = R.color.white)
            )
            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = colorResource(id = R.color.element_background))
                    .clickable { showPayingType.value = !showPayingType.value }
            ) {
                Text(
                    text = payingTypeState.value,
                    fontFamily = font_m_regular,
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
        if (payBottonFlag.value) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.yellow)
                ),
                onClick = {viewModel.createOrder()},
                content = {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = orderStr.value,
                            fontFamily = font_m_regular,
                            fontSize = 15.sp,
                            color = colorResource(id = R.color.background)

                        )
                    }
                }
            )
        } else Spacer(modifier = Modifier.weight(1f))

    }
}

