package com.app.alektapp.di

import com.app.alektapp.domain.provider.LessonProvider
import com.app.alektapp.domain.provider.WordsProvider
import com.app.alektapp.domain.services.SubscriptionService
import com.app.alektapp.viewModel.readingViewModel.ReadingViewModel
import com.app.alektapp.viewModel.subscriptionViewModel.SubscriptionViewModel
import com.app.alektapp.viewModel.vocabularyViewModel.VocabularyViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.kodein.di.*
import kotlin.time.Duration.Companion.seconds

enum class Module {
        Reading, Words, Subscription;
}

class AlektApp {
    companion object {
        lateinit var kodeinContainer: LazyDI
        val readingViewModel by lazy {
            ReadingViewModel(
                lessonProvider = kodeinContainer.direct.instance(),
                wordsProvider = kodeinContainer.direct.instance()
            )
        }

        val vocaublaryViewModel by lazy {
            VocabularyViewModel(
                provider = kodeinContainer.direct.instance(),
            )
        }

        val subscriptionViewModel by lazy {
            SubscriptionViewModel(
                service = di()
            )
        }

        var isDebug: Boolean = false
            private set


        inline fun <reified T : Any>  di() = kodeinContainer.direct.instance<T>()


        private fun initDebug(isDegub: Boolean) {
            isDebug = isDegub
            if (!isDegub) return
            Napier.base(DebugAntilog())
        }

        fun initApp(
            readingRepo: LessonProvider,
            wordsRepo: WordsProvider,
            isDebug: Boolean,
            subscriptionService: SubscriptionService
        ) {
            initDebug(isDebug)

            val subscriptionModule = DI.Module(Module.Subscription.name) {
                    subscriptionService.init()
                    subscriptionService.setDebug(isDebug)
                    bindSingleton { subscriptionService }
            }

            val prononcModule = DI.Module(Module.Reading.name) {
                bindSingleton { readingRepo }
            }

            val vocabularyModule = DI.Module(Module.Words.name) {
                bindSingleton { wordsRepo }
            }

            kodeinContainer = DI.lazy {
                importAll(prononcModule, vocabularyModule, subscriptionModule)
            }
        }

        fun reloadModels() {
            readingViewModel.reload(2.seconds)
            vocaublaryViewModel.reload(2.seconds)
        }
    }
}