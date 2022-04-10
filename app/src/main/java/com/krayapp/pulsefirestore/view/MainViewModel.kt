package com.krayapp.pulsefirestore.view

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.krayapp.pulsefirestore.model.HealthInfo
import com.krayapp.pulsefirestore.repo.DataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.random.Random

class MainViewModel(private val repo: DataSource):ViewModel() {

    private val _dataFlow = MutableStateFlow(mutableListOf(HealthInfo( 0, "",0,"")))
    val dataFlow:StateFlow<MutableList<HealthInfo>> = _dataFlow.asStateFlow()
    private var baseJob: Job? = null
    private val mainScope = CoroutineScope(Dispatchers.IO + SupervisorJob())


    fun addHealthInfo(){
        baseJob?.cancel()
        baseJob = mainScope.launch {
            repo.addHealthInfo(generateHealthInfo())
        }
    }

    fun getHealthList(){
        baseJob?.cancel()
        baseJob = mainScope.launch {
         _dataFlow.value = repo.getData()
        }
    }

    private suspend fun generateHealthInfo():HealthInfo{
        val lowPressure = Random.nextInt(50, 80)
        val highPressure = Random.nextInt(110, 140)
        val pulse = Random.nextInt(60, 100)
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val date = LocalDateTime.now().format(formatter)
        delay(2000L)
        return HealthInfo(pulse = pulse, pressure = "${highPressure / lowPressure}", date = date)
    }
}