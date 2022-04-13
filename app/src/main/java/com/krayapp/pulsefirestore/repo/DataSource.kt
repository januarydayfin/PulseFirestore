package com.krayapp.pulsefirestore.repo

import com.krayapp.pulsefirestore.model.HealthInfo

interface DataSource {
    fun addHealthInfo(healthInfo: HealthInfo)
    fun clearHistory()
     fun loadData(): MutableList<HealthInfo>
}