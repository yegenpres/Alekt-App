package com.app.alektapp.android.views.prononciation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.alektapp.android.views.navigation.prononciation

@Composable
fun Prononciation(navController: NavHostController) {
   NavHost(navController = navController, startDestination = "/") {
       prononciation(navController, mainPage = {
           LessonsList(navController)
       }) { nawController, lessonId ->
           PrononcTaskScreen(lessonId)
       }
   }
}