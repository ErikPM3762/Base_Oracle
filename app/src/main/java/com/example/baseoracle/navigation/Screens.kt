package com.example.baseoracle.navigation

object Screens {
    val COMO_IR = Screen(route = "como_ir")
    val RUTAS = Screen(route = "rutas")
    val TARIFAS = Screen(route = "tarifas")

    data class Screen(val route: String)
}