package com.example.baseoracle.ui.rate

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RateScreen(
    navController : NavController
) {
    ScreenContent(text = "Pantalla Tarifa")
}

@Composable
fun ScreenContent(text: String) {
    Text(
        text = text,
        style = TextStyle(fontSize = 24.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Black
    )
}