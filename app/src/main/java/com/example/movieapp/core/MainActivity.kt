package com.example.movieapp.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.core.presentation.HomeScreen
import com.example.movieapp.movielist.util.Screen
import com.example.movieapp.ui.theme.MovieAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {

                SetBarColor(MaterialTheme.colorScheme.onPrimaryContainer)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ){
                        composable(Screen.Home.route) {
                            HomeScreen(navController = navController)
                        }

                        composable(Screen.Details.route + "/{movieId}",
                            arguments = listOf(
                                navArgument("movieId") {type = NavType.IntType}
                            )
                        ) { backStackEntry ->
                            //DetailsScreen(backStackEntry)
                        }
                    }

                }
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color){
        val systemUiController = rememberSystemUiController()

        LaunchedEffect(key1 = color) {
            systemUiController.setSystemBarsColor(color)
        }

    }
}