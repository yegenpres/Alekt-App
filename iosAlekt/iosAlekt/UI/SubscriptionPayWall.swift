//
//  SubscriptionPayWall.swift
//  iosAlekt
//
//  Created by 111 on 07.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct SubscriptionPayWall: View {
    let offers: Set<SubscriptionProduct>
    let onSubscribe: (SubscriptionProduct) -> Void
    let onTest: () -> Void
    
    init(
        offers: Set<SubscriptionProduct>,
        onClick: @escaping (SubscriptionProduct) -> Void,
        onTest: @escaping () -> Void
    ) {
        self.offers = offers
        self.onSubscribe = onClick
        self.onTest = onTest
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 40) {
            Spacer()
            Benefits()
            Spacer()
            ForEach(Array(offers), id: \.self) { offer in
                Offer(offer: offer, onClick: onSubscribe)
                    .onLongPressGesture(minimumDuration: 1) {
                        onTest()
                    }
            }
            Spacer()
            Docs()
        }
    }
}

private struct Benefits: View {
    
    private var benefits: Array<String> {
        let kArray = PaywallBenefits.values()
        var result = [String]()
        
        for i in 0..<kArray.size {
            result.append(kArray.get(index: i)?.description() ?? "empty")
        }
       
        return result
    }
    
    var body: some View {
        VStack {
            ForEach(benefits, id: \.self) { benefit in
                Punkt(text: benefit)
            }
        }
    }
}

private struct Punkt: View {
    let text: String
    
    var body: some View {
        HStack {
            Group {
                Image(systemName: "checkmark.seal")
                    .foregroundColor(Color(uiColor: .systemGreen))
                Text(text)
                    .padding(.leading)
            }
            .font(.title2)
            .padding()
            Spacer()
        }
    }
}

private struct Offer: View {
    let offer: SubscriptionProduct
    let onSubscribe: (SubscriptionProduct) -> Void

    init(offer: SubscriptionProduct, onClick: @escaping (SubscriptionProduct) -> Void) {
        self.offer = offer
        self.onSubscribe = onClick
    }
    
    var body: some View {
        Button(action: { onSubscribe(offer) }) {
            HStack {
                VStack(alignment: .leading) {
                    Text(offer.title)
                    Text(offer.description_)
                }
                Spacer()
                Text(offer.price)
            }
            .frame(maxWidth: .infinity)
        }
        .buttonStyle(.borderedProminent)
        .padding()
    }
}

private struct Docs: View {
    
    var body: some View {
        HStack {
            Spacer()
            Link(Documents.privacyPolicy.title, destination: URL(string: Documents.privacyPolicy.url)!)
                .foregroundColor(.blue)
            Spacer()
            Link(Documents.termsOfUsage.title, destination: URL(string: Documents.termsOfUsage.url)!)
                .foregroundColor(.blue)
            Spacer()
        }
    }
}


struct PaywallPreview_Previews: PreviewProvider {
    
    static var previews: some View {
        SubscriptionPayWall(offers: Mock().subscriptionViewState().offers) { _ in
            
        } onTest: {
            
        }
    }
}
