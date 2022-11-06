package com.bignerdranch.android.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bignerdranch.android.myapplication.view.ITunesDetailsScreen
import com.bignerdranch.android.myapplication.view.getItunes

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ItunesListScreen.route) {
        composable(
            route = Screen.ItunesListScreen.route) {
            getItunes(navController = navController)
        }
        composable(
            route = Screen.ItunesDetailsScreen.route+"?trackName={trackName}?artwork30={artwork30}?longDescription={longDescription}",
            arguments = listOf(
                navArgument("trackName") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("artwork30") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("longDescription") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            ITunesDetailsScreen(
                trackName = entry.arguments?.getString("trackName"),
                artImage = entry.arguments?.getString("artwork30"),
                longDesc = entry.arguments?.getString("longDescription"),
            )
        }
    }

}
fun route(base: String, vararg argNames: String): String {
    val argString = argNames.joinToString(
        prefix = "?".takeIf { argNames.isNotEmpty() } ?: "",
        separator = "&",
    ) { "$it={$it}" }
    return "$base$argString"
}