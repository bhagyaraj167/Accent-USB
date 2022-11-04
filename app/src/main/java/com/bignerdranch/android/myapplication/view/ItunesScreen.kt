package com.bignerdranch.android.myapplication.view

import android.annotation.SuppressLint

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.bignerdranch.android.myapplication.viewmodel.ItunesViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun getItunes(itunesViewModel: ItunesViewModel = hiltViewModel()) {
    itunesViewModel.getItunes("jack+johnson","musicVideo")
}

