package com.fransbudikashira.core.domain.usecase

import com.fransbudikashira.core.domain.model.Movie
import com.fransbudikashira.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: IMovieRepository
): MovieUseCase {

    override fun searchMovie(search: String) = movieRepository.searchMovie(search)

    override fun getMovieById(movieId: String) = movieRepository.getMovieById(movieId)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}