package com.fransbudikashira.core.data.source.remote.network

import com.fransbudikashira.core.data.source.remote.response.MovieResponse
import com.fransbudikashira.core.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") search: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): MovieResponse
}