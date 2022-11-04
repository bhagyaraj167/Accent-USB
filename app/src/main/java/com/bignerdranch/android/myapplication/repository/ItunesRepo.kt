package com.bignerdranch.android.myapplication.repository

import com.bignerdranch.android.myapplication.model.Itunes
import com.bignerdranch.android.myapplication.network.APIService
import com.bignerdranch.android.myapplication.network.APIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItunesRepo @Inject constructor(val apiService: APIService) {
    suspend fun getItunesData(term: String, entity: String): Flow<APIState<Itunes>>{
        return flow{
            val list = apiService.getItunesData(term, entity)
            emit(APIState.success(list))
        }
    }
}