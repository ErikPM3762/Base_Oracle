package com.example.baseoracle.navigation

import com.example.baseoracle.R

object Screens {
    val COMO_IR = Screen(route = "como_ir", R.drawable.ic_travel, "Viajar")
    val RUTAS = Screen(route = "rutas", R.drawable.ic_route, "Líneas")
    val TARIFAS = Screen(route = "tarifas", R.drawable.ic_rechargue, "Mobility Pay")

    val STOP = Screen(route = "stop", icon = -1, label = "")
    val LINE = Screen(route = "line", icon = -1, label = "")
    val DETAIL_LINE = Screen(route = "detailLine", icon = -1, label = "")

    data class Screen(
        val route: String,
        val icon: Int,
        val label: String)
}