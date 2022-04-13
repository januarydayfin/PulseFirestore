package com.krayapp.pulsefirestore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.krayapp.pulsefirestore.R
import com.krayapp.pulsefirestore.databinding.ActivityMainBinding
import com.krayapp.pulsefirestore.toast
import com.krayapp.pulsefirestore.view.Adapter.MainAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModel()
    private val viewBinding: ActivityMainBinding by viewBinding()
    private val healthAdapter = MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fabClickListener()
        viewBinding.healthRecycler.adapter = healthAdapter
        viewModel.getHealthList()
        viewModel.dataFlow.onEach { list -> healthAdapter.submitList(list) }
            .launchIn(lifecycleScope)
    }

    private fun fabClickListener() {
        with(viewBinding) {
            pulseFab.setOnClickListener {
                viewModel.addHealthInfo()
                viewBinding.healthRecycler.scrollToPosition(0)
            }
            pulseFab.setOnLongClickListener {
                viewModel.cleanHistory()
                return@setOnLongClickListener true
            }
        }


    }
}