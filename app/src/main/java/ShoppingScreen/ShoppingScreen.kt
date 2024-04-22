package ShoppingScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composelesson.AccountScreen.AccountHeader
import com.example.composelesson.MenuScreen.Meel
import com.example.composelesson.R

@SuppressLint("RememberReturnType")
@Composable
fun ShoppingScreen() {
    val caveat = FontFamily(Font(R.font.caveat, FontWeight.Normal))
    val montserrat_regular = FontFamily(Font(R.font.montserratalternatesregular, FontWeight.Normal))
    val montserrat_light = FontFamily(Font(R.font.montserratalternateslight, FontWeight.Normal))
    val montserrat_semibold = FontFamily(Font(R.font.montserratalternatessemibold, FontWeight.Normal))
    val shoppingList = remember { mutableStateOf<List<Meel>>(emptyList()) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .padding(horizontal = 10.dp)
    ) {
        AccountHeader(
            font_caveat = caveat,
        )
        ShoppingList(
            font_m_semibold = montserrat_semibold,
            font_m_regular = montserrat_regular,
            font_m_light =  montserrat_light,
            shoppingList = shoppingList
            )


    }
}