package com.krayapp.pulsefirestore.model

data class HealthInfo(
    var id:Int = 0 ,
    val pressure: String?,
    val pulse: Int?,
    val date: String?
)

fun HealthInfo.setId(id:Int){
    this.id = id
}