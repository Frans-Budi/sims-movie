package com.fransbudikashira.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fransbudikashira.core.utils.Constants.MOVIE_TABLE

@Entity(tableName = MOVIE_TABLE)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("imdbID")
    var imdbID: String,

    @ColumnInfo("title")
    var title: String,

    @ColumnInfo("year")
    var year: String,

    @ColumnInfo("poster")
    var poster: String,

    @ColumnInfo("type")
    var type: String,

    @ColumnInfo("isFavorite")
    var isFavorite: Boolean
)