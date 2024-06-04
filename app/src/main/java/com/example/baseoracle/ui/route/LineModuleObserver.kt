package com.example.baseoracle.ui.route

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.Observable
import androidx.navigation.NavController
import com.example.baseoracle.R
import com.example.baseoracle.navigation.Screens
import com.mobilityado.common.utils.MSPreferencesManager
import com.mobilityado.data.TypeApp
import com.mobilityado.stopdetailmodule.StopDetailInfoModule
import com.movilityado.linesmodule.LineModuleInfo

class LineModuleObserver(
    private val navController: NavController,
    private val baseContext: Context,
    private val showArrow: (Boolean) -> Unit,
    private val onTitleUpdated: (String) -> Unit
) {

    init {
        observeTitleChanges()
        observeRouteIdChanges()
        observeStopIdChanges()
    }

    private fun observeTitleChanges() {
        LineModuleInfo.getInfoLines().getUpdateTitleLines()
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    val nameMacroRegion = LineModuleInfo.getInfoLines().getDescMacroRegion()
                    val title = baseContext.getString(R.string.obs_route)
                    onTitleUpdated("$title $nameMacroRegion")
                    showArrow(true)
                    navController.navigate(Screens.LINE.route)
                }
            })
    }

    private fun observeRouteIdChanges() {
        LineModuleInfo.getInfoLines().getIdRoute()
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    val title = baseContext.getString(R.string.obs_detail_route)
                    onTitleUpdated(title)
                    showArrow(true)
                    navController.navigate(Screens.DETAIL_LINE.route)
                }
            })
    }

    private fun observeStopIdChanges() {
        LineModuleInfo.getInfoLines().getIdStop()
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showArrow(true)
                    StopDetailInfoModule.setInfoAppCompany(baseContext, 5, TypeApp.AVANZA_REGIONS)
                    StopDetailInfoModule.getInfoStop().setInfoStop(busLineID = LineModuleInfo.getInfoLines().getIdRouteS(),
                        busStopID = LineModuleInfo.getInfoLines().getIdStop().get().toString())
                    navController.navigate(Screens.STOP.route)
                }
            })
    }
}


