package com.example.baseoracle

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baseoracle.navigation.BottomNavigationBar
import com.example.baseoracle.navigation.Navigation
import com.example.baseoracle.navigation.Screens
import com.example.baseoracle.theme.BaseOracleTheme
import com.example.baseoracle.theme.Gotham
import com.example.baseoracle.ui.route.LineModuleObserver
import com.mobilityado.common.utils.MSPreferencesManager
import com.movilityado.data.TypeApp
import com.movilityado.linesmodule.LineModuleInfo
import dagger.hilt.android.AndroidEntryPoint

private const val DESC_REGION = "Benidorm"

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private val idLocalCompany = 5
    private var currentTitle: String by mutableStateOf("")
    private var showTopBar: Boolean by mutableStateOf(false)
    private var showArrow: Boolean by mutableStateOf(false)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LineModuleInfo.setInfoAppCompany(baseContext, idLocalCompany, TypeApp.AVANZA_REGIONS)
        LineModuleInfo.getInfoLines().setInfoMacroRegion(42.toString(), DESC_REGION)

        setContent {
            BaseOracleTheme {
                navController = rememberNavController()

                observeNavController(navController)
                LineModuleObserver(
                    navController = navController,
                    baseContext = baseContext,
                    showArrow = { showArrow = it },
                    onTitleUpdated = { title -> currentTitle = title }
                )

                Scaffold(
                    topBar = {
                        if (showTopBar) {
                            TopAppBar(
                                title = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        if (showArrow) {
                                            IconButton(onClick = { onBackPressed() }) {
                                                Icon(
                                                    painter = painterResource(R.drawable.arrow_right),
                                                    contentDescription = "Back",
                                                    tint = Color.Black
                                                )
                                            }
                                        }
                                        Text(
                                            text = currentTitle,
                                            fontFamily = Gotham,
                                            fontWeight = FontWeight.Light,
                                            fontSize = 18.sp,
                                            color = Color.Black
                                        )
                                    }
                                }
                            )
                        }
                    },
                    bottomBar = { BottomNavigationBar(navController) }
                ) {
                    Surface(modifier = Modifier.padding(it)) {
                        Navigation(
                            navController = navController,
                            idLocalCompany = idLocalCompany,
                            fragmentActivity = this
                        )
                    }
                }
            }
        }
    }

    private fun observeNavController(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.route) {

                //Control de header items
                Screens.RUTAS.route -> {
                    showArrow = false
                    showTopBar = true
                    currentTitle = baseContext.getString(R.string.top_bar_route)
                }

                Screens.TARIFAS.route -> {
                    showArrow = false
                    showTopBar = true
                    currentTitle = baseContext.getString(R.string.item_recharge)
                }

                //Control de Header App

                Screens.LINE.route,  Screens.DETAIL_LINE.route -> {
                    showArrow = true
                    showTopBar = true
                }

                Screens.STOP.route -> {
                    showArrow = true
                    showTopBar = true
                    currentTitle = baseContext.getString(R.string.top_bar_stop_detail)
                }

                else -> {
                    showTopBar = false
                    currentTitle = ""
                }
            }
        }
    }

    override fun onBackPressed() {
        //Los ID screen son de la pantalla de donde vienen
        when (navController.currentDestination?.route) {
            Screens.STOP.route -> {
                currentTitle = baseContext.getString(R.string.obs_detail_route)
            }
            Screens.DETAIL_LINE.route -> {
                val nameMacroRegion = LineModuleInfo.getInfoLines().getDescMacroRegion()
                val title = baseContext.getString(R.string.obs_route)
                currentTitle = ("$title $nameMacroRegion")
            }
        }
        super.onBackPressed()
    }
}
