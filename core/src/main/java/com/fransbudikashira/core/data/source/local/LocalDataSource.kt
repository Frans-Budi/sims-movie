package com.fransbudikashira.core.data.source.local

import com.fransbudikashira.core.data.source.local.entity.MovieEntity
import com.fransbudikashira.core.data.source.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun searchMovies(search: String) = movieDao.searchMovies(search)

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    suspend fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getMovieById(id: String) = movieDao.getMovieById(id)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}