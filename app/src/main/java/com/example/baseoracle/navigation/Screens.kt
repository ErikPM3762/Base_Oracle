package com.example.baseoracle.navigation

import com.example.baseoracle.R

object Screens {
    val COMO_IR = Screen(route = "como_ir", R.drawable.ic_go, "Como Ir")
    val RUTAS = Screen(route = "rutas", R.drawable.ic_route, "Rutas")
    val TARIFAS = Screen(route = "tarifas", R.drawable.ic_rechargue, "Recargas")

    val STOP = Screen(route = "stop", icon = -1, label = "")
    val LINE = Screen(route = "line", icon = -1, label = "")
    val DETAIL_LINE = Screen(route = "detailLine", icon = -1, label = "")

    data class Screen(
        val route: String,
        val icon: Int,
        val label: String)
}