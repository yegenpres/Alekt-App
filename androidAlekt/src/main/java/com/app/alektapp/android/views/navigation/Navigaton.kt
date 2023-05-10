package com.app.alektapp.android.views.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

fun NavGraphBuilder.prononciation(navController: NavHostController, mainPage: @Composable (NavHostController) -> Unit ,taskPage: @Composable (NavHostController, String?) -> Unit) {
    navigation(startDestination = Screen.PrononciationMain.route, route = "/") {
        composable(Screen.PrononciationMain.route) { mainPage(navController) }
        composable(
            "${Screen.PrononciationTasks.route}/{lessonId}",
            arguments = listOf(navArgument("lessonId") { type = NavType.StringType }),
            ) {  backStackEntry ->
            taskPage(navController, backStackEntry.arguments?.getString("lessonId"))
        }
    }
}

