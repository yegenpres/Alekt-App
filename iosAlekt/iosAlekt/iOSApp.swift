import SwiftUI
import shared
import AWSAPIPlugin
import AWSDataStorePlugin
import AWSCognitoAuthPlugin
import AWSS3StoragePlugin
import Speech
import Amplify


@main
struct iOSApp: App {
    var hubEventListener: UnsubscribeToken
    
    init() {
        configureAmplify()
        
#if DEBUG
        let isDebug = true
#else
        let isDebug = true
#endif
        
        let appFlyer = AppFlyer()
        appFlyer.initService(waitToInit: 30, isDebug: isDebug)
        
        AlektApp.companion.doInitApp(
            readingRepo: AWSReadingTaskProvider(),
            wordsRepo: AWSWordProvider(),
            isDebug: isDebug,
            subscriptionService: Subscription(appFlyer: appFlyer)
        )
        
        hubEventListener = Amplify.Hub.listen(to: .dataStore) { event in
            if event.eventName == HubPayload.EventName.DataStore.ready {
                AlektApp.companion.reloadModels()
            }
        }
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .onReceive(NotificationCenter.default.publisher(for: UIApplication.didBecomeActiveNotification)) { _ in
                    AppFlyer.askPermit()
                    
                    SFSpeechRecognizer.requestAuthorization { status in
                        switch status {
                        case .authorized:
                            // Authorization granted, continue with your code
                            break
                        case .denied:
                            // Authorization denied, inform the user
                            break
                        case .restricted:
                            // Authorization restricted, inform the user
                            break
                        case .notDetermined:
                            // Authorization not determined, prompt the user to grant permission
                            break
                        @unknown default:
                            fatalError("Unknown status returned from requestAuthorization")
                        }
                    }
                    }
        }
    }
}

func configureAmplify() {
    let apiPlugin = AWSAPIPlugin(modelRegistration: AmplifyModels())
    let dataStorePlugin = AWSDataStorePlugin(modelRegistration: AmplifyModels())
    do {
        try Amplify.add(plugin: apiPlugin)
        try Amplify.add(plugin: dataStorePlugin)
        try Amplify.add(plugin: AWSCognitoAuthPlugin())
        try Amplify.add(plugin: AWSS3StoragePlugin())
        try Amplify.configure()
        
        
        print("Initialized Amplify");
    } catch {
        // simplified error handling for the tutorial
        print("Could not initialize Amplify: \(error)")
    }
}

func cleanStore() async {
    do {
        try await Amplify.DataStore.clear()
        print("DataStore cleared")
    } catch let error as DataStoreError {
        print("Error clearing DataStore: \(error)")
    } catch {
        print("Unexpected error \(error)")
    }
}
