package com.fransbudikashira.core.domain.model

data class Movie(
    val imdbID: String,
    val poster: String,
    val title: String,
    val type: String,
    val year: String,
    val isFavorite: Boolean
)