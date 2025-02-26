package com.nairi.ideaplatform

import android.app.Application
import com.nairi.ideaplatform.data.di.dataModule
import com.nairi.ideaplatform.domain.di.domainModule
import com.nairi.ideaplatform.ui.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule + domainModule + presentationModule)
        }
    }
}