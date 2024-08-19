package com.fransbudikashira.core.data.source.remote

import com.fransbudikashira.core.data.source.remote.network.ApiResponse
import com.fransbudikashira.core.data.source.remote.network.ApiService
import com.fransbudikashira.core.data.source.remote.response.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    fun searchMovies(search: String): Flow<ApiResponse<List<SearchItem>>> {
        return flow {
            try {
                val response = apiService.searchMovies(search)
                if(response.response.toBoolean()) {
                    val dataArray = response.search
                    if(dataArray?.isNotEmpty() == true) emit(ApiResponse.Success(response.search))
                    else emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error(response.error.toString()))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}