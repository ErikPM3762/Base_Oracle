package com.example.baseoracle.navigation

import android.util.Log
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
import com.example.baseoracle.ui.home.HomeScreen
import com.example.baseoracle.ui.route.MacroRegionsScreen
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
                HomeScreen(navController)
            }

            composable(Screens.RUTAS.route) {
                if (idLocalCompany == 53) {
                    MacroRegionsScreen(fragmentActivity)
                } else {
                    MacroRegionsScreen(fragmentActivity)
                }
            }

            composable(Screens.TARIFAS.route) {
                RateScreen(navController)
            }

            // Navegacion app
            composable(Screens.STOP.route) {
                StopScreen(fragmentActivity)
            }

            composable(Screens.LINE.route) {
                LineScreen(fragmentActivity)
            }

            composable(Screens.DETAIL_LINE.route) {
                DetailLineScreen(fragmentActivity)
            }

            // Navegacion modulos para manejar el back
            if (navController.currentDestination?.route == Screens.COMO_IR.route) {
                fragmentActivity.finish()
            }
        }
    }
}

