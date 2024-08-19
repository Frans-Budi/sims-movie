package com.fransbudikashira.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fransbudikashira.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE title LIKE '%' || :search || '%'")
    fun searchMovies(search: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE imdbID = :id")
    fun getMovieById(id: String): Flow<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}