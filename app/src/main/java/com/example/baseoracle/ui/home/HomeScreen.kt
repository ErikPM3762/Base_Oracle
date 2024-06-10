package com.example.baseoracle.ui.home

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import com.example.baseoracle.MainActivity
import com.example.baseoracle.applyStatusBarConfig
import com.example.baseoracle.resetStatusBarConfig
import com.mobilityado.mm.common.utils.*


@Composable
fun HomeScreen(
    fragmentActivity: FragmentActivity
) {
    val fragmentManager = fragmentActivity.supportFragmentManager

    val latDefault = 23.634501
    val longDefault = -102.552784

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager.newInstance(context) }
    preferencesManager[PreferencesManager.Companion.Key.ID_COMPANY] = 11
    preferencesManager[PreferencesManager.Companion.Key.APP_TYPE] = TypeApp.URBANO
    preferencesManager.saveLocation(latDefault, longDefault)
    val xx = fragmentActivity.supportFragmentManager
    xx.fragments.forEach { fragment ->
        Log.d("FragmentTag", "Fragment: ${fragment::class.java.simpleName}, Tag: ${fragment.tag}")
    }

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
