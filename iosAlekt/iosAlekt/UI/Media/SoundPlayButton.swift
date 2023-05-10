
//
//  SoundPlayerCustom.swift
//  iosAlectApp
//
//  Created by 111 on 15.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import AVFoundation
import Combine

struct SoundPlayButoon<Content: View>: View {
    @State private var player: AVAudioPlayer?
    @State private var isPlaying: Bool
    
    
    init(base64: String, @ViewBuilder content: @escaping (@escaping() -> Void) -> Content) {
        if let data = Data(base64Encoded: base64, options: .ignoreUnknownCharacters) {
            let filename = URL.documentsDirectory.appendingPathComponent("outpur")
            do {
                try data.write(to: filename, options: .atomic)
                self.player = try AVAudioPlayer(contentsOf: filename)
            } catch {
                print(error)
            }
            
        } else {
            self.player = nil
        }
        self.content = content
        self.isPlaying = false
    }
    
    let content: (@escaping() -> Void) -> Content
    
    func play() {
        if let player {
            if isPlaying {
                player.pause()
            } else {
                player.play()
            }
            return
        }
        self.player?.prepareToPlay()
        player?.play()
        
    }
    
    var body: some View {
        content(play)
    }
}

struct SoundPlayer: View {
    
    @StateObject var player = AVPlayerSliderViewModel()

    let url: String
    
    var body: some View {
        HStack {
            playButton
            slider
        }
        .onAppear {
            print("onPlayer app \(url)")
            player.setItem(url: url)
        }
        .onDisappear {
            print("onPlayer diss \(url)")
            player.pause()
        }
        
    }
    
    var playButton: some View {
            Button(action: {
                if self.player.isPlaying {
                    self.player.pause()
                } else {
                    self.player.play()
                }
            }) {
                Image(systemName: self.player.isPlaying ? "pause.circle" : "play.circle")
                    .padding(.horizontal)
                    .imageScale(.large)
                    .scaleEffect(1.3)
            }
    }
    
    var slider: some View {
        Slider(value: $player.progressValue) { didChange in
            player.didSliderChanged(didChange)
               }
    }
}


