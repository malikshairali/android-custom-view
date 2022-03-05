package com.example.customview.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.customview.data.persistence.DatabaseDao
import com.example.customview.data.model.Mood
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val dataSource: DatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val list: Flow<PagingData<Mood>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = true)
    ) {
        dataSource.getMoods()
    }
        .flow
        .cachedIn(viewModelScope)

    fun setMood(mood: Mood) {
        uiScope.launch {
            insertMood(mood)
        }
    }

    private suspend fun insertMood(mood: Mood) {
        withContext(Dispatchers.IO) {
            dataSource.insert(mood)
        }
    }

}