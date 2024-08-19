package com.fransbudikashira.simsmovie

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.fransbudikashira.simsmovie.ui.component.*
import com.fransbudikashira.simsmovie.ui.navigation.Screen
import com.fransbudikashira.simsmovie.ui.screen.splash.SplashScreen
import com.fransbudikashira.simsmovie.ui.screen.detail.DetailScreen
import com.fransbudikashira.simsmovie.ui.screen.favorite.FavoriteScreen
import com.fransbudikashira.simsmovie.ui.screen.home.HomeScreen
import com.fransbudikashira.simsmovie.ui.screen.profile.ProfileScreen
import com.fransbudikashira.simsmovie.ui.theme.SimsMovieTheme

@Composable
fun SimsMovieApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when(currentRoute) {
                Screen.Home.route -> {
                    HomeTopAppBar(
                        onClickSearchButton = {
                            //
                        },
                        modifier = Modifier
                    )
                }
                Screen.Profile.route -> {
                    MyTopAppBar(title = stringResource(R.string.profile_title_appbar))
                }
                Screen.Favorite.route -> {
                    MyTopAppBar(title = stringResource(R.string.favorite_title_appbar))
                }
            }
        },
        bottomBar = {
            if(currentRoute != Screen.Splash.route &&
                currentRoute != Screen.Search.route &&
                currentRoute != Screen.Detail.route)
                MyBottomAppBar(navController)
        },
        modifier = Modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController)
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    onClick = { movie ->
                        navController.navigate(
                            Screen.Detail.createRoute(movie.imdbID)
                        )
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    onClick = { movie ->
                        navController.navigate(
                            Screen.Detail.createRoute(movie.imdbID)
                        )
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Search.route) {
//                SearchScreen(
//                    onClick = { movie ->
//                        navController.navigate(
//                            Screen.Detail.createRoute(movie.imdbID)
//                        )
//                    }
//                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("movieId") { type = NavType.StringType })
            ) {
                val movieId = it.arguments?.getString("movieId") ?: ""
                DetailScreen(
                    movieId = movieId,
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SimsMovieAppPreview() {
    SimsMovieTheme {
        SimsMovieApp()
    }
}