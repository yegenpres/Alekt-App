
//
//  WordView.swift
//  iosAlectApp
//
//  Created by 111 on 25.04.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct WordView: View {
    let word: WordBase
    let language: Languages

    func fethcSound() -> String {
        word.id
    }
    
    var body: some View {
            HStack {
                Text(word.wordDK)
                Spacer()
                Group {
                    if language == .eng {
                        Text(word.translateEng)
                    } else {
                        Text(word.translateRu)
                    }
                }
                SoundPlayButoon(base64: word.audio.value) { play in
                    Button {
                        play()
                    } label: {
                        Image(systemName: "play.circle")
                            .foregroundColor(.blue)
                    }
                    .buttonStyle(.borderless)
                }.padding(.leading)
            }
    }
}
