package com.app.alektapp.android.views.navigation

import androidx.annotation.StringRes
import com.app.alektapp.android.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object PrononciationMain : Screen("PrononciationMain", R.string.PrononciationMain)
    object PrononciationTasks : Screen("PrononciationTasks", R.string.PrononciationTasks)
    object Test : Screen("Test", R.string.Test)
    object Vocabulary: Screen("Vocabulary", R.string.Vocabulary)
    object ReadingMain: Screen("ReadingMain", R.string.ReadingMain)
    object ReadingTask: Screen("ReadingTask", R.string.ReadingTask)
    object PaywallScrean: Screen("PaywallScrean", R.string.PaywallScrean)
}

infix fun Screen.PrononciationTasks.withLessonId(id: String): String {
    return "${this.route}/${id}"
}

infix fun Screen.ReadingTask.withLessonId(id: String): String {
    return "${this.route}/${id}"
}