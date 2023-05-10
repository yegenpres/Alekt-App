package tests.provider

import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.app.alektapp.android.configureAmplify
import com.app.alektapp.android.model.AWSReadingTaskProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.net.URL


@SmallTest
class RepositoriesReadingTest {
    lateinit var context: android.content.Context
    lateinit var repo: AWSReadingTaskProvider

    val idItem = "14aa86dc-8f64-4604-842b-4f93a1b60a7f"


    private fun runTest(call: suspend ()-> Unit) {
        runBlocking {
            launch {
                call()
            }
        }
    }


    @Before
    fun createLogHistory() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        configureAmplify(context)

        repo = AWSReadingTaskProvider()
    }

    @Test
    fun repositoryReadingTest_fetchListOflessons() = runTest {
        val lessons = repo.fetchListOfLesson()
        assert(lessons.count() > 1)
    }

    @Test
    fun repositoryReadingTest_fetchItem() = runTest {
        val item = repo.fetch(idItem)
        assert(item.isNotEmpty())
    }

    @Test
    fun repositoryReadingTest_fetchMedia() = runTest {
        val media = repo.fetchMedia(idItem)

         try {
            val obj = URL(media.value)
            obj.toURI()
        } catch (e: Exception) {
            println(e)
        }

        assert(media.value.isNotEmpty())
    }
}


