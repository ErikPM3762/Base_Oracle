package com.example.baseoracle.ui.stops

import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment

@Composable
fun StopScreen(fragmentActivity: FragmentActivity) {
    val fragmentManager = fragmentActivity.supportFragmentManager

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
            val navGraphResourceId = com.mobilityado.featurestopdetail.R.navigation.nav_graph_stop_detail

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




