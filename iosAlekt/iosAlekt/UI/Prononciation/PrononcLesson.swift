
//
//  PrononsLesson.swift
//  iosAlectApp
//
//  Created by 111 on 15.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import AVKit

struct PrononsLesson: View {
    private let url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    let id: Int
    
    var body: some View {
        List {
            VideoPlayerCustom(url: url)
                .listRowBackground(Color.clear)
            Exercise()
            Exercise()
        }
    }
}
