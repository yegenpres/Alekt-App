package com.app.alektapp.android.model

import android.util.Log

import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.Level
import com.amplifyframework.datastore.generated.model.ReadingTask
import com.app.alektapp.domain.model.LessonBase
import com.app.alektapp.domain.model.LessonTittleBase
import com.app.alektapp.domain.model.LevelBase
import com.app.alektapp.domain.model.SoundUrl
import com.app.alektapp.domain.provider.LessonProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

fun Level.toBase(): LevelBase =
    when (this) {
        Level.A1 -> LevelBase.A1
        Level.A2 -> LevelBase.A2
        Level.B1 -> LevelBase.B1
        Level.B2 -> LevelBase.B2
        Level.C1 -> LevelBase.C1
        Level.C2 -> LevelBase.C2
    }


fun ReadingTask.toBase(): LessonBase =
    LessonBase(
        header = LessonTittleBase(this.id, this.title, this.subTitle),
        text = this.text,
        level = this.level.toBase(),
        soundUrl = SoundUrl(this.soundPath),
    )

fun ReadingTask.toTitleBase(): LessonTittleBase =
    LessonTittleBase(this.id, this.title, this.subTitle)

class AWSReadingTaskProvider: LessonProvider() {
    override suspend fun fetch(id: String): Set<LessonBase> = suspendCancellableCoroutine { cor ->
        Amplify.DataStore.query(
            ReadingTask::class.java,
            Where.matches(ReadingTask.ID.eq(id)),
            { items ->
                val result = mutableListOf<LessonBase>()
                while (items.hasNext()) {
                    result.add(items.next().toBase())
                }

                cor.resume(result.toSet())
            },
            { failure ->
                Log.e("ReadingTask", "Could not query DataStore", failure)
                cor.resumeWithException(failure)
            }
        )
    }

    override suspend fun fetchMedia(path: String): SoundUrl = fetch(path).first().soundUrl

    override suspend fun fetchListOfLesson(): Set<LessonTittleBase> = suspendCancellableCoroutine { cor ->
        Amplify.DataStore.query(
            ReadingTask::class.java,
            { items ->
                val result = mutableListOf<LessonTittleBase>()
                while (items.hasNext()) {
                    result.add(items.next().toTitleBase())
                }

                cor.resume(result.toSet())
            },
            { failure ->
                Log.e("ReadingTask", "Could not query DataStore", failure)
                cor.resumeWithException(failure)
            }
        )
    }
}

