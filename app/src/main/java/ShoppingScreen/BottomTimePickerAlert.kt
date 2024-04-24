package ShoppingScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTimePickerAlert(
    font_m_regular: FontFamily,
    showPicker: MutableState<Boolean>,
    hourState: MutableState<String>,
    minuteState: MutableState<String>,
) {
    ModalBottomSheet(
        containerColor = colorResource(id = R.color.background),
        onDismissRequest = { showPicker.value = !showPicker.value }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Привезем заказ до ",
                    fontFamily = font_m_regular,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.white)
                )
                Spacer(modifier = Modifier.weight(1f))
                MyTimePicker(
                    font_m_regular = font_m_regular,
                    HourState = hourState,
                    MinuteState = minuteState
                )
            }
            
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {showPicker.value = !showPicker.value},
                content = {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Выбрать",
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