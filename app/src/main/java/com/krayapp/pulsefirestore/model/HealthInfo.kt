package com.krayapp.pulsefirestore.model

data class HealthInfo(
    var id:String = " " ,
    val pressure: String,
    val pulse: String,
    val date: String
)

fun HealthInfo.setId(id:Int){
    this.id = id
}