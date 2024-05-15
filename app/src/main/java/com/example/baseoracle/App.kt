package com.example.baseoracle

import android.app.Application
import com.example.services.utils.AppIdManager
import com.example.services.utils.Environment
import com.example.services.utils.EnvironmentManager
import com.mobilityado.data.App
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    init {
        System.loadLibrary("secure")
    }

    override fun onCreate() {
        super.onCreate()
        setupConfigurationApp()
    }

    private fun setupConfigurationApp(){
        EnvironmentManager.setEnvironment(Environment.QA)
        AppIdManager.setIdLocalCompany(App.AHORROBUS.idLocalCompany)
    }
}