//
//  ViewModelUtils.swift
//  iosAlekt
//
//  Created by 111 on 07.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

import SwiftUI
import Combine
import shared


private class ObservableModel<StateObserved>: ObservableObject {
    @Published var stateObserved: StateObserved?
    
    init(statePublisher: AnyPublisher<StateObserved, Never>) {
            statePublisher
            .compactMap { $0 }
            .receive(on: DispatchQueue.main)
            .assign(to: &$stateObserved)
    }
}

public struct ObservingView<StateObserved, Content>: View where Content: View {
    @ObservedObject private var model: ObservableModel<StateObserved>
    
    private let content: (StateObserved) -> Content
    
    public init(statePublisher: AnyPublisher<StateObserved, Never>, @ViewBuilder content: @escaping (StateObserved) -> Content) {
        self.model = ObservableModel(statePublisher: statePublisher)
        self.content = content
    }
    
    public var body: some View {
        let view: AnyView
        
        if let viewState = self.model.stateObserved {
            view = AnyView(content(viewState))
        } else {
            view = AnyView(ZStack {
                
            })
        }
        
        return view
    }
}

func statePublisher<T>(_ flow: KviewmodelWrappedStateFlow<T>) -> AnyPublisher<T, Never> {
    return Deferred<Publishers.HandleEvents<PassthroughSubject<T, Never>>>() {
        let subject = PassthroughSubject<T, Never>()
        
        let closeable = flow.watch {next in
            subject.send(next)
        }
        
        return subject.handleEvents(receiveCancel: {
            closeable.close()
        })
    }.eraseToAnyPublisher()
}

func sharePublisher<T>(_ flow: KviewmodelWrappedSharedFlow<T>) -> AnyPublisher<T, Never> {
    return Deferred<Publishers.HandleEvents<PassthroughSubject<T, Never>>>() {
        let subject = PassthroughSubject<T, Never>()
        
        let closeable = flow.watch {next in
            if let next = next {
                subject.send(next)
            }
        }
        
        return subject.handleEvents(receiveCancel: {
            closeable.close()
        })
    }.eraseToAnyPublisher()
}

