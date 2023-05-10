
//  NewWordsTable.swift
//  iosAlectApp
//
//  Created by 111 on 29.04.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct NewWordsTable: View {
    let words: [WordBase]
    
    var body: some View {
        Section {
            VStack {
                ForEach(words, id: \.id) { word in
                    HStack {
                        Text(word.wordDK)
                        Spacer()
                        VStack{
                            Text(word.translateEng)
                            Text(word.translateRu)
                        }
                        Button {
                            
                        } label: {
                            SoundPlayButoon(base64: word.audio.value) { play in
                                Button(action: play ) {
                                    Image(systemName: "volume")
                                }
                            }
                        }
                        .padding(.trailing)
                    }
                    .padding()
                    .font(.title2)
                    Divider()
                }
            }
        }
        .buttonStyle(BorderlessButtonStyle())
    }
}
