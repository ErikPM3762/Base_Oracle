package com.example.baseoracle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.baseoracle.navigation.BottomNavigationBar
import com.example.baseoracle.navigation.Navigation
import com.example.baseoracle.navigation.Screens
import com.example.baseoracle.theme.BaseOracleTheme
import com.example.services.utils.AppIdManager
import com.movilityado.linesmodule.LineModuleInfo
import dagger.hilt.android.AndroidEntryPoint

private const val DESC_REGION = "Ahorrobus"

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private val idLocalCompany = AppIdManager.getIdLocalCompany()
    private var currentRoute: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LineModuleInfo.setInfoAppCompany(baseContext, idLocalCompany)
        LineModuleInfo.getInfoLines().setInfoMacroRegion(42.toString(), DESC_REGION)
        setContent {
            BaseOracleTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { /* Puedes colocar el título aquí */ },
                            navigationIcon = {
                                IconButton(onClick = { onBackPressed() }) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                            }
                        )
                    },
                    bottomBar = { BottomNavigationBar(navController) }
                ) {
                    Surface(modifier = Modifier.padding(it)) {
                        Navigation(navController = navController, idLocalCompany, this)
                    }
                }
            }
        }
        observeNavController(navController)
    }

    private fun observeNavController(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute = destination.route
        }
    }

    override fun onBackPressed() {
        if (currentRoute == Screens.COMO_IR.route) {
            // Si el usuario está en la pantalla de inicio, cierra la actividad
            finish()
        } else {
            // Si no está en la pantalla de inicio, realiza el retroceso de la navegación
            super.onBackPressed()
        }
    }
}
