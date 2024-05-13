package com.example.baseoracle.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baseoracle.ui.home.HomeScreen
import com.example.baseoracle.ui.rate.RateScreen
import com.example.baseoracle.ui.route.RouteScreen

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        modifier = Modifier.navigationBarsPadding(),
        navController = navController as NavHostController,
        startDestination = Screens.COMO_IR.route
    ) {
        composable(Screens.COMO_IR.route) {
            HomeScreen(navController)
        }

        composable(Screens.RUTAS.route) {
            RateScreen(navController)
        }

        composable(Screens.TARIFAS.route) {
            RouteScreen(navController)
        }
    }
}
