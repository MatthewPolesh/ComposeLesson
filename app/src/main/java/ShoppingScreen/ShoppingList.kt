package ShoppingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.MenuScreen.Item
import com.example.composelesson.MenuScreen.Meel
import com.example.composelesson.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingList(
    font_m_semibold: FontFamily,
    font_m_regular: FontFamily,
    font_m_light: FontFamily,
    shoppingList: MutableState<List<Meel>>
) {
    var orderSum = 0
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {

        Text(
            text = "Заказ",
            fontSize = 20.sp,
            color = colorResource(id = R.color.white),

            )
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            LazyColumn(
            ) {
                itemsIndexed(shoppingList.value) { index, item ->
                    Item(
                        font_m_semibold = font_m_semibold,
                        font_m_regular = font_m_regular,
                        font_m_light = font_m_light,
                        meel = item
                    )
                }

            }
        }

        Divider(modifier = Modifier.padding(top = 15.dp, bottom = 5.dp))

        OutlinedTextField(
            value = "",
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = colorResource(id = R.color.white),
                unfocusedIndicatorColor = colorResource(id = R.color.white),
                containerColor = colorResource(id = R.color.element_background),
                cursorColor = colorResource(id = R.color.yellow)
            ),
            shape = RoundedCornerShape(15.dp),
            textStyle = TextStyle(
                color = colorResource(id = R.color.white),
                //fontFamily = font_m_regular,
                fontSize = 15.sp

            ),
            onValueChange = { },
            label = {
                Text(
                    text = "Комментарий к заказу",
                    //fontFamily = font_m_light,
                    modifier = Modifier.padding(0.dp),
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.white)
                )
            },
        )

        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = "Доставка",
            fontSize = 20.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(bottom = 15.dp)
        )

        DeliveryBox(
            font_m_light = FontFamily.Default,
            font_m_semibold = FontFamily.Default,
            font_m_regular = FontFamily.Default
        )
        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Row(
            modifier = Modifier.padding(bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Заказ приготовим",
                fontSize = 15.sp,
                color = colorResource(id = R.color.white)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Сегодня до 17.50",
                fontSize = 15.sp,
                color = colorResource(id = R.color.white)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = colorResource(id = R.color.element_background))
                    .clickable { }
            ) {
                Text(
                    text = "Как можно скорее",
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = colorResource(id = R.color.element_background))
                    .clickable { }
            ) {
                Text(
                    text = "Ко времени",
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.white),
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
                fontSize = 15.sp,
                color = colorResource(id = R.color.white)
            )
            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = colorResource(id = R.color.element_background))
                    .clickable { }
            ) {
                Text(
                    text = "Картой онлайн",
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.yellow)
            ),
            onClick = {},
            content = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material.Text(
                        text = "Оплатить ${shoppingList.value.forEach { orderSum += it.price }}",
                        //fontFamily = font_m_regular,
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.background)

                    )
                }
            }
        )

    }
}
