package com.krayapp.pulsefirestore.repo

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.krayapp.pulsefirestore.model.HealthInfo
import kotlinx.coroutines.*

class FirestoreRepo(private val collection: CollectionReference) : DataSource {
    private val ID: String = "ID"
    private val PRESSURE: String = "PRESSURE"
    private val PULSE: String = "PULSE"
    private val DATE: String = "DATE"
    private val healthHash = HashMap<String, Any>()

    private var checkMutableList: MutableList<HealthInfo> =
        mutableListOf()

    private var baseJob: Job? = null
    private val mainScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private fun addDataToFirebase(healthInfo: HealthInfo) {
        healthHash[ID] = healthInfo.id
        healthHash[PRESSURE] = healthInfo.pressure
        healthHash[PULSE] = healthInfo.pulse
        healthHash[DATE] = healthInfo.date
        collection
            .add(healthHash)
    }

    private fun clearHistoryFromFirebase() {
        for (item in checkMutableList) {
            collection.document(item.id).delete()
        }
        checkMutableList = mutableListOf()
    }

    override fun loadData(): MutableList<HealthInfo> {
        val mutableList: MutableList<HealthInfo> = mutableListOf()
        collection.orderBy("DATE", Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (item in task.result) {
                        val id = item.id
                        mutableList.add(toHealthInfo(id, item.data))
                    }
                    checkMutableList = mutableList
                }
            }
        return checkMutableList
    }

    private fun toHealthInfo(id: String, map: Map<String, Any>): HealthInfo =
        HealthInfo(
            id = id,
            pressure = map[PRESSURE].toString(),
            pulse = map[PULSE].toString(),
            date = map[DATE].toString()
        )

    override  fun addHealthInfo(healthInfo: HealthInfo) {
        baseJob?.cancel()
        baseJob = mainScope.launch {
            addDataToFirebase(healthInfo)
        }
    }

    override fun clearHistory() {
        baseJob?.cancel()
        baseJob = mainScope.launch {
            clearHistoryFromFirebase()
        }
    }

}