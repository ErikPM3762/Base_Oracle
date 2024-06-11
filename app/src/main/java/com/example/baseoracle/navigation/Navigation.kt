package com.example.baseoracle.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baseoracle.applyStatusBarConfig
import com.example.baseoracle.resetStatusBarConfig
import com.example.baseoracle.ui.home.HomeScreen
import com.example.baseoracle.ui.rate.RateScreen
import com.example.baseoracle.ui.route.DetailLineScreen
import com.example.baseoracle.ui.route.LineScreen
import com.example.baseoracle.ui.stops.StopScreen

val LocalFragmentActivity = compositionLocalOf<FragmentActivity?> { null }

@Composable
fun Navigation(
    navController: NavController,
    idLocalCompany: Int,
    fragmentActivity: FragmentActivity
) {
    CompositionLocalProvider(LocalFragmentActivity provides fragmentActivity) {
        NavHost(
            modifier = Modifier.navigationBarsPadding(),
            navController = navController as NavHostController,
            startDestination = Screens.COMO_IR.route
        ) {
            // Navegacion de navbar
            composable(Screens.COMO_IR.route) {
                fragmentActivity.applyStatusBarConfig(true)
                HomeScreen(fragmentActivity)
            }

            composable(Screens.RUTAS.route) {
                fragmentActivity.resetStatusBarConfig()
                if (idLocalCompany == 53) {
                    LineScreen(fragmentActivity)
                } else {
                    LineScreen(fragmentActivity)
                }
            }

            composable(Screens.TARIFAS.route) {
                fragmentActivity.resetStatusBarConfig()
                RateScreen(navController)
            }

            // Navegacion app
            composable(Screens.STOP.route) {
                fragmentActivity.resetStatusBarConfig()
                StopScreen(fragmentActivity)
            }

            composable(Screens.DETAIL_LINE.route) {
                fragmentActivity.resetStatusBarConfig()
                DetailLineScreen(fragmentActivity)
            }

            // Navegacion modulos para manejar el back
            if (navController.currentDestination?.route == Screens.COMO_IR.route) {
                fragmentActivity.finish()
            }
        }
    }
}

