package ShoppingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.material3.TimeInput
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelesson.R


@Composable
fun MyTimePicker(
    font_m_regular: FontFamily,
    HourState: MutableState<String>,
    MinuteState: MutableState<String>,
) {
    val hourState by remember { mutableStateOf(HourState) }
    val minuteState by remember { mutableStateOf(MinuteState) }
    val scroll = rememberScrollState()
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .height(60.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.element_background))

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(60.dp)
                    .padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "00 01 02 03 04 12 13 14 15",
                    modifier = Modifier.verticalScroll(scroll),
                    textAlign = TextAlign.Center,
                    fontFamily = font_m_regular,
                    fontSize = 35.sp,
                    color = colorResource(id = R.color.white),
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(shape = CircleShape)
                        .background(color = colorResource(id = R.color.yellow))
                )

                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(shape = CircleShape)
                        .background(color = colorResource(id = R.color.yellow))
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(60.dp)
                    .padding(horizontal = 5.dp)
            ) {
                BasicTextField(
                    value = minuteState.value,
                    onValueChange = { if (it.length < 3) minuteState.value = it },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = font_m_regular,
                        fontSize = 35.sp,
                        color = colorResource(id = R.color.white)
                    ),
                )
            }
        }
    }
}