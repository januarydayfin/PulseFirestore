package com.krayapp.pulsefirestore.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.krayapp.pulsefirestore.R
import com.krayapp.pulsefirestore.model.HealthInfo

class MainAdapter:ListAdapter<HealthInfo,MainViewHolder>(MainDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
         MainViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.health_template,parent,false))


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}