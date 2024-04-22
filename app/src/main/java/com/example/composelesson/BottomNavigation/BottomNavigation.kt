package com.example.composelesson.BottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composelesson.R

@Composable
fun BottomNavigation(
    navController: NavController?
) {
    val listItems = listOf(
        BottomItem.Screen1,
        BottomItem.Screen2,
        BottomItem.Screen3
    )
    val backStackEntry = navController?.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.value?.destination?.route
    Box(
        modifier = Modifier
            .background(color = colorResource(id = R.color.background))
            .clip(
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )

    ) {
        NavigationBar(
            modifier = Modifier.height(70.dp),
            containerColor = colorResource(id = R.color.element_background),

            ) {
            listItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = item.route == currentRoute,
                    onClick = { navController?.navigate(item.route) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.yellow),
                        selectedTextColor = colorResource(id = R.color.yellow),
                        indicatorColor = colorResource(id = R.color.element_background),
                        unselectedIconColor = colorResource(id = R.color.white),
                        unselectedTextColor = colorResource(id = R.color.white),
                    ),

                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconId),
                            contentDescription = "icon",
                            modifier = Modifier.size(20.dp),
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    },
                )
            }
        }
    }


}