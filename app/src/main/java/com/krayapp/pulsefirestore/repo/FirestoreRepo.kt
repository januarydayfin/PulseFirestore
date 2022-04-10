package com.krayapp.pulsefirestore.repo

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import com.krayapp.pulsefirestore.model.HealthInfo

class FirestoreRepo(private val collection: CollectionReference) : DataSource {
    private val ID: String = "ID"
    private val PRESSURE: String = "PRESSURE"
    private val PULSE: String = "PULSE"
    private val DATE: String = "DATE"
    private val healthHash = HashMap<String, Any>()

    private var checkMutableList:MutableList<HealthInfo> = mutableListOf() // Нужен для очистки FireBase

    override suspend fun addHealthInfo(healthInfo: HealthInfo) {
        healthHash[ID] = healthInfo.id
        healthHash[PRESSURE] = healthInfo.pressure
        healthHash[PULSE] = healthInfo.pulse
        healthHash[DATE] = healthInfo.date
        collection
            .add(healthHash)
    }

    override suspend fun clearHistory() {
        for (item in checkMutableList){
            collection.document(item.id).delete()
        }
        checkMutableList = mutableListOf()
    }

    override suspend fun getData(): MutableList<HealthInfo> {
        val mutableList: MutableList<HealthInfo> = mutableListOf()
        collection.get()
            .addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
                override fun onComplete(task: Task<QuerySnapshot>) {
                    if (task.isSuccessful) {
                        for (item in task.result) {
                            var id = item.id
                            mutableList.add(toHealthInfo(id, item.data))
                        }
                    }
                }
            })
        mutableList.reverse()
        checkMutableList = mutableList
        return mutableList
    }

    private fun toHealthInfo(id: String, map: Map<String, Any>): HealthInfo =
        HealthInfo(
            id = id,
            pressure = map[PRESSURE].toString(),
            pulse = map[PULSE].toString(),
            date = map[DATE].toString()
        )


}