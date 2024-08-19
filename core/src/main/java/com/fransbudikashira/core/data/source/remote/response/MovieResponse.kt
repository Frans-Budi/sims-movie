package com.fransbudikashira.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("Response")
	val response: String,

	@field:SerializedName("totalResults")
	val totalResults: String? = null,

	@field:SerializedName("Search")
	val search: List<SearchItem>? = null,

	@field:SerializedName("Error")
	val error: String? = null
)

data class SearchItem(

	@field:SerializedName("Type")
	val type: String,

	@field:SerializedName("Year")
	val year: String,

	@field:SerializedName("imdbID")
	val imdbID: String,

	@field:SerializedName("Poster")
	val poster: String,

	@field:SerializedName("Title")
	val title: String
)
