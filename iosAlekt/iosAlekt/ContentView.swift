import SwiftUI
import shared

struct ContentView: View {
    private let subscriptionModel = AlektApp.companion.subscriptionViewModel
    
    var body: some View {
        TabView {
            ObservingView(statePublisher: statePublisher(subscriptionModel.viewStates())) { model in
                if model.status == SubscriptionStatus.IsSubscribed() {
                    ReadingPage()
                } else {
                    SubscriptionPayWall(offers: model.offers) { product in
                        subscriptionModel.obtainEvent(viewEvent: SubscriptionEvents.Subscribe(Context: (Any).self, product: product))
                    } onTest: {
                        subscriptionModel.obtainEvent(viewEvent: SubscriptionEvents.TestSubscribe())
                    }
                }
            } .tabItem {
                Label("Reading", systemImage: "books.vertical.circle")
            }
            
            VocabularyPage()
                .tabItem {
                    Label("Vocabulary", systemImage: "archivebox.fill")
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    
    init() {
        configureAmplify()
        
        
#if DEBUG
        let isDebug = true
#else
        let isDebug = true
#endif
        
        AlektApp.companion.doInitApp(
            readingRepo: AWSReadingTaskProvider(),
            wordsRepo: AWSWordProvider(),
            isDebug: isDebug,
            subscriptionService: Subscription(appFlyer: AppFlyer())
        )
    }
    
    static var previews: some View {
        ContentView()
    }
}




