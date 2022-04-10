package com.krayapp.pulsefirestore.repo

import com.google.firebase.firestore.CollectionReference
import com.krayapp.pulsefirestore.model.HealthInfo

class FirestoreRepo(private val collection: CollectionReference):DataSource {
    override suspend fun addHealthInfo(healthInfo: HealthInfo) {
        collection.add()
    }

    override suspend fun clearHistory() {
        TODO("Not yet implemented")
    }

    override suspend fun getData():MutableList<HealthInfo> {
        TODO("Not yet implemented")
    }
}