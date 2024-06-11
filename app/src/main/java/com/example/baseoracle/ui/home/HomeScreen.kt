package com.example.baseoracle.ui.home

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import com.mobilityado.mm.common.utils.PreferencesManager
import com.mobilityado.mm.common.utils.TypeApp


@Composable
fun HomeScreen(
    fragmentActivity: FragmentActivity
) {
    val fragmentManager = fragmentActivity.supportFragmentManager

    val latDefault = 38.5501
    val longDefault = -0.1418

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager.newInstance(context) }
    preferencesManager[PreferencesManager.Companion.Key.ID_COMPANY] = 5
    preferencesManager[PreferencesManager.Companion.Key.APP_TYPE] = TypeApp.AVANZA_REGIONS
    preferencesManager.saveLocation(latDefault, longDefault)

    // Creación del contenedor del Fragment
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            FragmentContainerView(ctx).apply {
                id = View.generateViewId()
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { containerView ->
            val navGraphResourceId = com.mobilityado.mm.map.R.navigation.mm_home_map_nav_graph

            if (fragmentManager.findFragmentById(containerView.id) == null) {
                val navHostFragment = NavHostFragment.create(navGraphResourceId)
                fragmentManager.beginTransaction()
                    .replace(containerView.id, navHostFragment)
                    .setPrimaryNavigationFragment(navHostFragment)
                    .commitNowAllowingStateLoss()
            }
        }
    )
}
