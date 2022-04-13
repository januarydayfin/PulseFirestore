package com.krayapp.pulsefirestore

import android.app.Application
import com.google.firebase.firestore.CollectionReference
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PulseApp:Application() {
    companion object{
        const val HEALTH_DATA = "HEALTH_DATA"
    }
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@PulseApp)
            modules(Koin.getModules())
        }
    }
}