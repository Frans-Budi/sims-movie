package com.fransbudikashira.core.domain.repository

import com.fransbudikashira.core.data.Resource
import com.fransbudikashira.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun searchMovie(search: String): Flow<Resource<List<Movie>>>

    fun getMovieById(movieId: String): Flow<Movie>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}