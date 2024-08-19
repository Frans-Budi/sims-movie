package com.fransbudikashira.core.data

import com.fransbudikashira.core.data.source.local.LocalDataSource
import com.fransbudikashira.core.data.source.remote.RemoteDataSource
import com.fransbudikashira.core.data.source.remote.network.ApiResponse
import com.fransbudikashira.core.data.source.remote.response.SearchItem
import com.fransbudikashira.core.domain.model.Movie
import com.fransbudikashira.core.domain.repository.IMovieRepository
import com.fransbudikashira.core.utils.AppExecutors
import com.fransbudikashira.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun searchMovie(search: String): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<SearchItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.searchMovies(search).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SearchItem>>> =
                remoteDataSource.searchMovies(search)

            override suspend fun saveCallResult(data: List<SearchItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()
    }

    override fun getMovieById(movieId: String): Flow<Movie> {
        return localDataSource.getMovieById(movieId).map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }


}