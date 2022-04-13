package com.krayapp.pulsefirestore

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.toast(context: Context, text:String){
    Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
}