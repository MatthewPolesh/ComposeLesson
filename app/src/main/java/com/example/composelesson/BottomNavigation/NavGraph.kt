package com.example.composelesson.BottomNavigation

import com.example.composelesson.ShoppingScreen.ShoppingScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composelesson.AccountScreen.AccountScreen
import com.example.composelesson.MainViewModel
import com.example.composelesson.MenuScreen.MenuScreen


@Composable
fun NavGraph(navHostController: NavHostController) {
    val viewModel = viewModel<MainViewModel>()

    NavHost(
        navController = navHostController,
        startDestination = Screens.SCREEN2.value,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },

        )
    {
        composable(Screens.SCREEN1.value) {
            AccountScreen(viewModel)
        }
        composable(Screens.SCREEN2.value) {
            MenuScreen(viewModel)
        }
        composable(Screens.SCREEN3.value) {
            ShoppingScreen(viewModel)
        }
    }
}
