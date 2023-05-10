
//
//  File.swift
//  iosAlectApp
//
//  Created by 111 on 15.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import AVKit


struct VideoPlayerCustom: View {
    let url: String
    
    var body: some View {
        VideoPlayer(player: AVPlayer(url:  URL(string: url)!))
            .cornerRadius(15)
            .aspectRatio(16/9, contentMode: .fill)
    }
}
