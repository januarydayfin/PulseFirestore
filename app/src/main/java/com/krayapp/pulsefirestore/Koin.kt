package com.krayapp.pulsefirestore

import android.annotation.SuppressLint
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.krayapp.pulsefirestore.PulseApp.Companion.HEALTH_DATA
import com.krayapp.pulsefirestore.repo.DataSource
import com.krayapp.pulsefirestore.repo.FirestoreRepo
import com.krayapp.pulsefirestore.view.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Koin {
    @SuppressLint("StaticFieldLeak")
    private val store = FirebaseFirestore.getInstance()
    fun getModules() = module {
        single { store.collection(HEALTH_DATA) }
        single<DataSource> { FirestoreRepo(collection = get()) }

        viewModel { MainViewModel(repo = get()) }
    }
}


