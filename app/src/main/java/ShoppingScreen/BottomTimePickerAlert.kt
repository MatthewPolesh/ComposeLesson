package ShoppingScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.MainViewModel
import com.example.composelesson.R
import kotlinx.coroutines.flow.asStateFlow

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTimePickerAlert(
    font_m_regular: FontFamily,
    font_m_semibold: FontFamily,
    showPicker: MutableState<Boolean>,
    viewModel: MainViewModel
) {
    val hours = List(200) {listOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23")}.flatMap { it }
    val minutes = List(200) {listOf("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55")}.flatMap { it }
    val selectedHourIndex = remember { mutableStateOf(0) }
    val selectedMinuteIndex = remember { mutableStateOf(0) }
    ModalBottomSheet(
        
        containerColor = colorResource(id = R.color.background),
        onDismissRequest =
        {
            showPicker.value = !showPicker.value
            viewModel.changeOrderTime(viewModel.orderHour.value,viewModel.orderMinute.value)
            viewModel.changeOrderTimeFlag()
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = "Выберите время доставки",
                    textAlign = TextAlign.Start,
                    color = colorResource(id = R.color.white),
                    fontFamily = font_m_semibold,
                    fontSize = 20.sp,
                    )
            }
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
                    SelectedHourIndex = selectedHourIndex,
                    SelectedMinuteIndex = selectedMinuteIndex,
                    Hours = hours,
                    Minutes = minutes
                )
            }
            
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {showPicker.value = !showPicker.value
                    viewModel.changeOrderTime(hours[selectedHourIndex.value],minutes[selectedMinuteIndex.value])
                          },
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