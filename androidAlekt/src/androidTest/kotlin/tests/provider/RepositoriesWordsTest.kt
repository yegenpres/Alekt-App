package tests.provider

import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.app.alektapp.android.configureAmplify
import com.app.alektapp.android.model.AWSReadingTaskProvider
import com.app.alektapp.android.model.AWSWordsProvider
import com.app.alektapp.domain.model.WordBase


import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


@SmallTest
class RepositoriesWordsTest {
    lateinit var context: android.content.Context
    lateinit var wordsRepo: AWSWordsProvider
    lateinit var readingRepo: AWSReadingTaskProvider

    private suspend fun searchMatch(query: String, api: suspend (AWSWordsProvider, String)-> Set<WordBase>, validator: (WordBase) -> String) {
        val wordDk = api(wordsRepo, query)

        var isOk = true

        if (wordDk.isEmpty()) {
            assert(false)
        }

        wordDk.forEach {
            isOk = validator(it).contains(query)
            if (!isOk) return@forEach
        }

        assert(isOk)
    }

    @Before
    fun createLogHistory() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        configureAmplify(context)

        wordsRepo = AWSWordsProvider()
        readingRepo = AWSReadingTaskProvider()
    }

    @Test
    fun wordsRepo_testtest() {
        runBlocking {
            launch {
                val words = wordsRepo.fetchMatchDK("nu")
                assertEquals(1, words.count())
            }
        }
    }

    @Test
    fun wordsRepo_fetchWordById() {
        runBlocking {
            launch {
                val words = wordsRepo.fetch(id = "øjneneeyes")
                assertEquals(1, words.count())
            }
        }
    }

    @Test
    fun wordsRepo_fetchOffsetWord() {
        runBlocking {
            launch {
                val count = 5

                val words = wordsRepo.fetch(start = 0, end = count)
                assertEquals(count, words.count())
            }
        }
    }

    @Test
    fun wordsRepo_fetchAlllWords() {
        runBlocking {
            launch {
                val words = wordsRepo.fetch()
                assert(words.isNotEmpty())
            }
        }
    }

    @Test
    fun wordsRepo_dkMatch() =  runTest {
            searchMatch("øl", api = { repo, query ->
                repo.fetchMatchDK(query)
            }) {
                it.wordDK
            }
        }

    @Test
    fun wordsRepo_dkEqual() =  runTest {
        searchMatch("sent", api = { repo, query ->
            repo.fetchEqualDK(query)
        }) {
            it.wordDK
        }
    }

    @Test
    fun wordsRepo_engMatch() = runTest {
        searchMatch("do", api = { repo, query ->
            repo.fetchMatchENG(query)
        }) {
            it.translateEng
        }
    }

    @Test
    fun wordsRepo_ruMatch() = runTest {
        searchMatch("о", api = { repo, query ->
            repo.fetchMatchRU(query)

        }) {
            it.translateRu
        }
    }

    @Test
    fun wordsRepo_dkMatchOffset() =  runTest {
        searchMatch("ø", api = { repo, query ->
            repo.fetchMatchDK(query, 0, 2)
        }) {
            it.wordDK
        }
    }

    @Test
    fun wordsRepo_engMatchOffset() = runTest {
        searchMatch("do", api = { repo, query ->
            repo.fetchMatchENG(query, 0, 10)
        }) {
            it.translateEng
        }
    }

    @Test
    fun wordsRepo_ruMatchOffset() = runTest {
        searchMatch("о", api = { repo, query ->
            repo.fetchMatchRU(query, 0, 10)

        }) {
            it.translateRu
        }
    }

    @Test
    fun wordRepo_fetchMedia() = runTest {
        val media = wordsRepo.fetchMedia("øjneneeyes")

        assert(media.value.isNotEmpty())
    }
}

fun runTest(call: suspend ()-> Unit) {
    runBlocking {
        launch {
            call()
        }
    }
}

