package com.krayapp.pulsefirestore.repo

import com.krayapp.pulsefirestore.model.HealthInfo

interface DataSource {
   suspend fun addHealthInfo(healthInfo: HealthInfo)
   suspend fun clearHistory()
   suspend fun getData():MutableList<HealthInfo>
}