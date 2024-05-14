package com.example.baseoracle.navigation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.baseoracle.theme.ahorrobusPrimary
import com.example.baseoracle.theme.navigationBackgroundSelect

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screens.COMO_IR,
        Screens.RUTAS,
        Screens.TARIFAS
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Gray
    ) {
        items.forEach { screen ->
            val selected = currentRoute == screen.route
            BottomNavigationItem(
                icon = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(56.dp, 30.dp)
                            .background(
                                color = if (selected) navigationBackgroundSelect else Color.Transparent,
                                shape = RoundedCornerShape(50)
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = screen.label,
                            tint = if (selected) ahorrobusPrimary else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                label = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = screen.label,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }

                },
                selected = selected,
                onClick = {
                    if (navController.currentDestination?.route != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                selectedContentColor = ahorrobusPrimary,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = true
            )
        }
    }
}
