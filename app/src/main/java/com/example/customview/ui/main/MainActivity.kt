package com.example.customview.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.*
import com.example.customview.data.persistence.Database
import com.example.customview.data.model.Mood
import com.example.customview.ui.base.ViewModelFactory
import com.example.customview.ui.custom.CustomView
import com.example.customview.utils.Utils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.customview.utils.Utils.Companion.toString

class MainActivity : AppCompatActivity() {

    private lateinit var list: RecyclerView
    private lateinit var happyButton: Button
    private lateinit var sadButton: Button
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataSource = Database.getInstance(application).databaseDao
        val viewModelFactory = ViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        happyButton = findViewById(R.id.btn_happy)
        sadButton = findViewById(R.id.btn_sad)

        happyButton.setOnClickListener { addMood(CustomView.HAPPY) }
        sadButton.setOnClickListener { addMood(CustomView.SAD) }

        list = findViewById(R.id.list)
        val adapter = ListAdapter()
        list.adapter = adapter

        lifecycleScope.launch {
            viewModel.list.collectLatest { adapter.submitData(it) }
        }
    }

    private fun addMood(mood: Long) {
        val dateAndTime = Utils.getCurrentDateTime()
        val dateAndTimeString = dateAndTime.toString("yyyy/MM/dd HH:mm:ss")
        viewModel.setMood(Mood(mood = mood, dateAndTime = dateAndTimeString))
    }
}