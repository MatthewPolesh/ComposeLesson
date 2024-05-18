package com.example.composelesson.ShoppingScreen


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.MainViewModel
import com.example.composelesson.MenuScreen.Item
import com.example.composelesson.MenuScreen.OrderItem
import com.example.composelesson.R


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListStatus(
    font_m_semibold: FontFamily,
    font_m_regular: FontFamily,
    font_m_light: FontFamily,
    viewModel: MainViewModel
) {


    val state = rememberScrollState()
    val shoppingList = viewModel.shoppingList.collectAsState()
    val adress = viewModel.selectedAdress.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .verticalScroll(state)

    ) {
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Заказ",
                fontFamily = font_m_semibold,
                fontSize = 20.sp,
                color = colorResource(id = R.color.white),
            )
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
                    OrderItem(
                        font_m_semibold = font_m_semibold,
                        font_m_regular = font_m_regular,
                        font_m_light = font_m_light,
                        meal = item,
                        viewModel = viewModel,
                    )
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = "Статус заказа",
            fontFamily = font_m_semibold,
            fontSize = 20.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(bottom = 15.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {

            Text(
                text = "Готовим",
                fontFamily = font_m_light,
                fontSize = 15.sp,
                color = colorResource(id = R.color.yellow),
            )
            Spacer(modifier = Modifier.weight(1f))
            Divider(modifier = Modifier.size(50.dp,1.dp))
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Везём",
                fontFamily = font_m_light,
                fontSize = 15.sp,
                color = colorResource(id = R.color.white),
            )
            Spacer(modifier = Modifier.weight(1f))
            Divider(modifier = Modifier.size(50.dp,1.dp))
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "У вас",
                fontFamily = font_m_light,
                fontSize = 15.sp,
                color = colorResource(id = R.color.white),
            )

        }

        Divider(modifier = Modifier.padding(top = 15.dp, bottom = 5.dp))

        OutlinedTextField(
            value = viewModel.orderComment.value,
            readOnly = true,
            modifier = Modifier
                .clickable(enabled = false,null,null) {}
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = colorResource(id = R.color.white),
                unfocusedIndicatorColor = colorResource(id = R.color.white),
                containerColor = colorResource(id = R.color.element_background),
                cursorColor = colorResource(id = R.color.yellow),
            ),
            shape = RoundedCornerShape(15.dp),
            textStyle = TextStyle(
                color = colorResource(id = R.color.white),
                fontFamily = font_m_regular,
                fontSize = 15.sp

            ),
            onValueChange = {},
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
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
        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Row(
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
                text = "cегодня до ${viewModel.selectedHour.value}:${viewModel.selectedMinute.value}",
                fontFamily = font_m_light,
                fontSize = 15.sp,
                color = colorResource(id = R.color.white)
            )
        }


        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 15.dp)
        ) {
            Text(
                text = "Спопоб оплаты",
                fontFamily = font_m_light,
                fontSize = 15.sp,
                color = colorResource(id = R.color.white)
            )
            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = colorResource(id = R.color.element_background))
            ) {
                Text(
                    text = viewModel.payingType.value,
                    fontFamily = font_m_light,
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(5.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}

