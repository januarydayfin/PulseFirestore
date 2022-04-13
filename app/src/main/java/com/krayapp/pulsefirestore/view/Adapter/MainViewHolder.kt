package com.krayapp.pulsefirestore.view.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.krayapp.pulsefirestore.databinding.HealthTemplateBinding
import com.krayapp.pulsefirestore.model.HealthInfo

class MainViewHolder(view: View)
    :RecyclerView.ViewHolder(view) {
    private val viewBinding:HealthTemplateBinding by viewBinding()
        fun bind(healthInfo: HealthInfo){
            with(viewBinding){
                date.text = healthInfo.date
                pressure.text = healthInfo.pressure
                pulse.text = healthInfo.pulse
            }
        }
}