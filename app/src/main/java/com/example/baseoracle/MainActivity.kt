package com.example.baseoracle

import android.os.Bundle
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baseoracle.navigation.BottomNavigationBar
import com.example.baseoracle.navigation.Navigation
import com.example.baseoracle.navigation.Screens
import com.example.baseoracle.theme.BaseOracleTheme
import com.example.baseoracle.theme.Gotham
import com.example.baseoracle.ui.route.LineModuleObserver
import com.movilityado.linesmodule.LineModuleInfo
import dagger.hilt.android.AndroidEntryPoint

private const val DESC_REGION = "Ahorrobus"

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private val idLocalCompany = 11
    private var currentTitle: String by mutableStateOf("")
    private var currentRoute: String? = null
    private var showTopBar: Boolean by mutableStateOf(false)
    private var showArrow: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LineModuleInfo.setInfoAppCompany(baseContext, idLocalCompany)
        LineModuleInfo.getInfoLines().setInfoMacroRegion(42.toString(), DESC_REGION)

        setContent {
            BaseOracleTheme {
                val navController = rememberNavController()

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
                                                    contentDescription = "Back"
                                                )
                                            }
                                        }
                                        Text(
                                            text = currentTitle,
                                            fontFamily = Gotham,
                                            fontWeight = FontWeight.Light,
                                            fontSize = 18.sp
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
            currentRoute = destination.route
            currentTitle = when (currentRoute) {
                Screens.RUTAS.route -> {
                    baseContext.getString(R.string.top_bar_route)
                }

                Screens.TARIFAS.route -> {
                    baseContext.getString(R.string.item_recharge)
                }

                Screens.STOP.route -> {
                    baseContext.getString(R.string.top_bar_stop_detail)
                }

                else -> ""
            }
            showTopBar = currentRoute != Screens.COMO_IR.route
        }
    }
}


