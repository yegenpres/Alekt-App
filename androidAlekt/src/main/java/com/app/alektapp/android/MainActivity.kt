package com.app.alektapp.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adeo.kviewmodel.BuildConfig
import com.adeo.kviewmodel.KViewModel
import com.app.alektapp.di.AlektApp
import com.app.alektapp.android.model.AWSReadingTaskProvider
import com.app.alektapp.android.model.AWSWordsProvider
import com.app.alektapp.android.purchases.Subscription
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.AmplifyConfiguration
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.DataStoreChannelEventName
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.hub.HubChannel
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureAmplify(applicationContext)

        AlektApp.initApp(
           readingRepo = AWSReadingTaskProvider(),
            wordsRepo = AWSWordsProvider(),
            isDebug = BuildConfig.DEBUG,
            subscriptionService = Subscription(applicationContext)
        )



        setContent {
            KViewModel.setupSharedExceptionHandler(CoroutineExceptionHandler { _, throwable ->
                Log.e("KVVIEW model exeption: ", throwable.message ?: "")
            })

            MyApplicationTheme {
                AlectApp()
            }
        }
    }
}

fun configureAmplify(ctx: android.content.Context) {
    try {
        val config = AmplifyConfiguration.builder(ctx)
            .devMenuEnabled(false)
            .build()

        Amplify.addPlugin(AWSApiPlugin())
        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.addPlugin(AWSDataStorePlugin())
        Amplify.addPlugin(AWSS3StoragePlugin())
        Amplify.configure(config, ctx)

        Amplify.Hub.subscribe(
            HubChannel.DATASTORE,
            { it.name.equals(DataStoreChannelEventName.READY.toString()) },
            {
                Log.i("MyAmplifyApp", "DataStore is ready")
                AlektApp.reloadModels()
            }
        )

        Log.i("Tutorial", "Initialized Amplify")
    } catch (error: AmplifyException) {
        Log.e("Tutorial", "Could not initialize Amplify", error)
    }
}


fun clearLocalDataStore(): Disposable {
    return Completable.create { emitter ->
        try {
            Amplify.DataStore.clear(
                { emitter.onComplete() },
                { error: DataStoreException -> emitter.onError(error) }
            )
        } catch (exception: Exception) {
            emitter.onError(exception)
        }
    }
        .subscribeOn(Schedulers.io())
        .subscribe(
            { Log.i("MyAmplifyApp", "Successfully cleared local datastore") },
            { error: Throwable -> Log.e("MyAmplifyApp", "Failed to clear local datastore", error) }
        )
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
    }
}
