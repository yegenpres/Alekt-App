//
//  ReadingExercise.swift
//  iosAlekt
//
//  Created by 111 on 07.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import AVFoundation
import Flow
import shared

typealias PlaceholderAlias = String
typealias AnswerAlias = String

extension String {
    func removeBrackets() -> String {
        self.replacingOccurrences(of: "{", with: "").replacingOccurrences(of: "}", with: "")
    }
}

struct ReadingExercise: View {
    @State private var answer = ""

    private var fullText: [String]
    private let wordsForTranslate: [WordBase]
    
    let showResult: (PlaceholderAlias) -> Bool?
    let setUnswer: (PlaceholderAlias, AnswerAlias) -> Void
        
    init(fullText: String, wordsForTranslate: [WordBase], showResult: @escaping (PlaceholderAlias) -> Bool?, setUnswer: @escaping (PlaceholderAlias, AnswerAlias) -> Void) {
        self.fullText = ParceTextForReadingView(text: fullText).build()
        self.wordsForTranslate = wordsForTranslate
        self.showResult = showResult
        self.setUnswer = setUnswer
    }
    
    var body: some View {
            HFlow( itemSpacing: 10, rowSpacing: 8) {
                ForEach(fullText, id: \.self) { chap in
                    if chap.hasPrefix("{{") {
                        InputWord(isTrue: showResult(chap.removeBrackets()),
                                  lenght: chap.count
                        ) { answer in
                            setUnswer(chap.removeBrackets(), answer)
                        }
                    } else if chap.hasPrefix("{") {
                        let word = wordsForTranslate.first { $0.wordDK == chap.removeBrackets() }
                        if let word {
                            SoundableWord(word: word)
                        } else {
                            Text(chap.removeBrackets())
                        }
                    } else {
                        Text(chap)
                    }
                }
            }
            .padding()
        }
}

struct SoundableWord: View {
    var word: WordBase
    
    var body: some View {
        SoundPlayButoon(base64: word.audio.value) { play in
            Text(word.wordDK)
                .foregroundColor(.blue)
                .onTapGesture {
                    play()
                }
                .contextMenu {
                    Text(word.translateEng)
                    Text(word.translateRu)
                }
        }
    }
}

struct InputWord: View {
    @State private var answer = ""
    
    private let color: Color
    private let isTrue: Bool?
    private let lenght: Int
    private let setUnswer: (String) -> Void
    
    init(isTrue: Bool?, lenght: Int, setUnswer: @escaping (String) -> Void) {
        self.isTrue = isTrue
        self.setUnswer = setUnswer
        self.lenght = lenght
        if let isTrue {
            self.color = isTrue ? Color.green.opacity(0.4) : Color.red.opacity(0.4)
        } else {
            self.color = Color.gray.opacity(0.2)
        }
    }
    
    var body: some View {
        let p = Binding<String>(
            get: {
                return self.answer
            },
            set: {
                self.answer = $0
                setUnswer($0)
            }
        )
        
        TextField("", text: p)
            .frame(width: CGFloat(lenght * 8))
            .padding(.horizontal)
            .background(color.opacity(0.4))
            .cornerRadius(.leastNormalMagnitude)
            .clipShape(RoundedRectangle(cornerRadius: 6, style: .circular))
            
    }
}
