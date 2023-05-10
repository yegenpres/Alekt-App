
//
//  ReadingTask.swift
//  iosAlectApp
//
//  Created by 111 on 06.04.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

import Foundation
import SwiftUI
import AVKit
import shared

struct ReadingTaskView: View {
    let item: LessonBase?
    let newWords: [WordBase]
    let wordsForTranslate: [WordBase]
    let loadLesson: () -> Void
    let checkLesson: () -> Void
    let showResult: (String) -> Bool?
    let setUnswer: (String, String) -> Void
    
    init(
        item: LessonBase?,
        newWords: [WordBase],
        wordsForTranslate: [WordBase],
        loadLesson: @escaping () -> Void,
        checkLesson: @escaping () -> Void,
        showResult: @escaping (String) -> Bool?,
        setUnswer: @escaping (String, String) -> Void
    ) {
        self.item = item
        self.newWords = newWords
        self.wordsForTranslate = wordsForTranslate
        self.loadLesson = loadLesson
        self.checkLesson = checkLesson
        self.showResult = showResult
        self.setUnswer = setUnswer
    }
    
    var body: some View {
        ViewOnBottomFooter {
            if let item {
                NewWordsTable(words: newWords)
                ReadingExercise(
                    fullText: item.text,
                    wordsForTranslate: wordsForTranslate,
                    showResult: showResult,
                    setUnswer: setUnswer
                )
                Button("Tjek") {
                    checkLesson()
                }
                .padding(.bottom, 80)
            } else {
                ProgressView()
            }
        } footer : { isOnBottom in
           
        }
        .onAppear {
            loadLesson()
        }
        .toolbar {
            ToolbarItem(placement: .bottomBar) {
                if let item {
                    SoundPlayer(url: item.soundUrl.value)
                        .padding()
                }
            }
        }
    }
}






struct ReadingTaskView_Preview: PreviewProvider {
    static var previews: some View {
        TabView {
            ReadingTaskView(
                item: Mock().lessonBase(index: 1),
                newWords: [],
                wordsForTranslate: [],
                loadLesson: {},
                checkLesson: {},
                showResult: {_ in true },
                setUnswer: {_,_ in  }
            )
            .tabItem {

            }
        }
    }
}


