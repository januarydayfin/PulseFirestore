package com.krayapp.pulsefirestore.view.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.krayapp.pulsefirestore.model.HealthInfo

object MainDiffUtil:DiffUtil.ItemCallback<HealthInfo>() {
    override fun areItemsTheSame(oldItem: HealthInfo, newItem: HealthInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HealthInfo, newItem: HealthInfo): Boolean {
        return (oldItem.date == newItem.date)
    }
}