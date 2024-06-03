@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.baseoracle.ui.route

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import com.example.baseoracle.theme.avanzaPrimary
import com.example.baseoracle.theme.avanzaPrimaryInt
import com.movilityado.common.extensions.getPrimaryAppColor
import com.movilityado.common.utils.ScreenLoadingDialog

@Composable
fun LineScreen(fragmentActivity: FragmentActivity) {
    val fragmentManager = fragmentActivity.supportFragmentManager
    val navHostFragmentId = remember { View.generateViewId() }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            FragmentContainerView(ctx).apply {
                id = navHostFragmentId
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { containerView ->
            if (fragmentManager.findFragmentById(containerView.id) == null) {
                val navGraphResourceId = com.movilityado.featureline.R.navigation.nav_graph_lines
                val navHostFragment = NavHostFragment.create(navGraphResourceId)
                fragmentManager.beginTransaction()
                    .replace(containerView.id, navHostFragment)
                    .setPrimaryNavigationFragment(navHostFragment)
                    .commit()
            }
        }
    )
}