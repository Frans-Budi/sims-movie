package com.fransbudikashira.simsmovie.ui.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object Home: Screen("home")
    object Favorite: Screen("favorite")
    object Profile: Screen("profile")
    object Search: Screen("search")
    object Detail: Screen("search/{movieId}") {
        fun createRoute(movieId: String) = "search/$movieId"
    }
}