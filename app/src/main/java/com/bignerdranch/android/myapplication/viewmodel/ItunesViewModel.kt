package com.bignerdranch.android.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.myapplication.model.Itunes
import com.bignerdranch.android.myapplication.network.APIState
import com.bignerdranch.android.myapplication.network.Status
import com.bignerdranch.android.myapplication.repository.ItunesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItunesViewModel @Inject constructor(val repository: ItunesRepo) : ViewModel() {
    val url = "https://itunes.apple.com/search?term=jack+johnson&entity=musicVideo"
    private val _ituneData = MutableStateFlow(
        APIState(
            Status.ERROR,
            Itunes(),
            null))

    val itunes = _ituneData.asStateFlow()

   public fun getItunes(term: String, entity: String) {
        _ituneData.value = APIState.loading()
        viewModelScope.launch {
            repository.getItunesData(term, entity)
                .catch {
                    _ituneData.value = APIState.error(it.message)
                    Log.d("bhagyarajj",it.message.toString())
                }
                .collect {
                    Log.d("bhagyarajj",it.data.toString())
                    _ituneData.value = APIState.success(it.data)
                }
        }

    }

}