package com.example.baseoracle.navigation

import com.example.baseoracle.R

object Screens {
    val COMO_IR = Screen(route = "como_ir", R.drawable.ic_go, "Como Ir")
    val RUTAS = Screen(route = "rutas", R.drawable.ic_route, "Rutas")
    val TARIFAS = Screen(route = "tarifas", R.drawable.ic_rechargue, "Recargas")

    data class Screen(
        val route: String,
        val icon: Int,
        val label: String)
}