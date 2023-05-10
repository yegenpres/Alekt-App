
//
//  Excercise.swift
//  iosAlectApp
//
//  Created by 111 on 15.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import AVFoundation


struct Exercise: View {
    
    var body: some View {
            Section {
                VStack {
                    HStack {
                        Spacer()
                        Text("word")
                            .font(.title2)
                            .padding(.trailing)
                        Button {
                            
                        } label: {
                            Image(systemName: "volume")
                        }
                        .padding(.trailing)
                    }
                    Divider()
                   Ansvers()
                }
            }
            .buttonStyle(BorderlessButtonStyle())
        }
}

private struct Ansvers: View {
    @State var correctAnswer = "A"
    @State var result = ""
    
    var body: some View {
        HStack(spacing: 50) {
            ForEach(["A", "B", "C"], id: \.self) { value in
                AnswerButton(
                    value: value,
                    isCorrect: value == correctAnswer,
                    showResult: !result.isEmpty
                ) {
                    result = value
                }
            }
        }
    }
}

private struct AnswerButton: View {
    @State private var isPressed = false
    @State private var color: Color = .blue
    let value: String
    let isCorrect: Bool
    var showResult: Bool = false
    let onClick: () -> Void
    
    var body: some View {
        SoundPlayButoon(
            base64: "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ) {  play in
            Button {
                play()
                
                if isPressed { return }
                
                onClick()
                isPressed = true
            } label: {
                Text(value)
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.bordered)
            .tint(color)
        }
        .onChange(of: showResult) {show in
            guard(show) else { return }
                    if (isCorrect) {
                     color = .green
                        return
                 }
                    if (isPressed) {
                        color = .red
                    }
        }
    }
}

struct Exercise_Preview: PreviewProvider {
    static var previews: some View {
        List {
            Exercise()
            Exercise()
        }
    }
}
