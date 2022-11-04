package com.bignerdranch.android.myapplication.network

import com.bignerdranch.android.myapplication.model.Itunes
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface APIService {
    @GET("search/")
    suspend fun getItunesData(
        @Query("term") term: String,
        @Query("entity") entity: String
        ):Itunes
}