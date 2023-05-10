
//
//  ReadingPage.swift
//  iosAlectApp
//
//  Created by 111 on 06.04.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension ReadingViewState {
    func booksSections() -> [(String, [LessonTittleBase])] {
        let groupedDictionary = Dictionary(grouping: self.titleSet) { item in
            String(item.subTitle)
        }
        return groupedDictionary.sorted { $0.0 < $1.0 }
    }
}



struct ReadingPage: View {
    private let viweModel = AlektApp.companion.readingViewModel
    private let som = Mock().readingViewState()
    
    var body: some View {
        ObservingView(statePublisher: statePublisher(viweModel.viewStates())) { (model: ReadingViewState) in
            NavigationStack {
                List(model.booksSections(), id: \.0) { book, chapters in
                    BookSection(
                        title: book,
                        lessons: chapters,
                        viewModel: viweModel,
                        state: model
                    )
                    }
                .listStyle(.sidebar)
                .navigationTitle("Reading")
                  }
        }
        
    }
    
}


struct BookSection: View {
    let title: String
    let lessons: [LessonTittleBase]
    let viewModel: ReadingViewModel
    let state: ReadingViewState
    
    var body: some View {
        Section(title) {
            ForEach(lessons, id: \.id) { chapter in
            NavigationLink {
                ReadingTaskView(
                    item: state.item,
                    newWords: Array(state.tasks.map { $0.word }),
                    wordsForTranslate: Array(state.wordsForTranslate)
                ) {
                    viewModel.obtainEvent(viewEvent: .LoadLesson(id: chapter.id))
                } checkLesson: {
                    viewModel.obtainEvent(viewEvent: .Check())
                } showResult: { placeholder in
                    if state.wrongAnswers.contains(placeholder) { return  false }
                    if state.correctAnswers.contains(placeholder) { return true }
                    return nil
                } setUnswer: { placeholder, unswer in
                    viewModel.obtainEvent(viewEvent: .MakeAnswer(
                        placeholder: placeholder,
                        value: unswer
                    )
                    )
                }
            } label: {
                Text(chapter.title)
            }
            }
        }
    }
}
