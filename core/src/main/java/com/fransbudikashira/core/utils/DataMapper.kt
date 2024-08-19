package com.fransbudikashira.core.utils

import com.fransbudikashira.core.data.source.local.entity.MovieEntity
import com.fransbudikashira.core.data.source.remote.response.SearchItem
import com.fransbudikashira.core.domain.model.Movie

object DataMapper {

    fun mapEntitiesToDomain(input: List<MovieEntity>) : List<Movie> =
        input.map {
            Movie(
                title = it.title,
                year = it.year,
                poster = it.poster,
                type = it.type,
                imdbID = it.imdbID,
                isFavorite = it.isFavorite
            )
        }

    fun mapResponsesToEntities(input: List<SearchItem>): List<MovieEntity> =
        input.map {
            MovieEntity(
                imdbID = it.imdbID,
                title = it.title,
                year = it.year,
                poster = it.poster,
                type = it.type,
                isFavorite = false,
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        imdbID = input.imdbID,
        title = input.title,
        year = input.year,
        poster = input.poster,
        type = input.type,
        isFavorite = input.isFavorite,
    )

    fun mapEntityToDomain(input: MovieEntity) = Movie(
        imdbID = input.imdbID,
        title = input.title,
        year = input.year,
        poster = input.poster,
        type = input.type,
        isFavorite = input.isFavorite,
    )
}